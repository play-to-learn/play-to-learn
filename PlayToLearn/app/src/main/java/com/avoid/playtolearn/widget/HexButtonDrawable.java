package com.avoid.playtolearn.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;

public class HexButtonDrawable extends Drawable{
    private Context context;

    private int numberOfSides = 6;
    private Path p_large = new Path();
    private Path p_small = new Path();
    private Path temporal = new Path();
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private int ashWhiteColor = Color.parseColor("#E6E6E6");
    private int greenColor = Color.parseColor("#004D40");

    private Bitmap bmp_scaled;
    private int bmp_width = 150;
    private int bmp_height = 150;

    public HexButtonDrawable(Context context) {
        this.context = context;

        p_large.setFillType(Path.FillType.EVEN_ODD);
    }

    @Override
    public void draw(Canvas canvas) {
        paint.setColor(ashWhiteColor);
        canvas.drawPath(p_large, paint);
        paint.setColor(greenColor);
        canvas.drawPath(p_small, paint);

        if (bmp_scaled != null) {
            canvas.drawBitmap(bmp_scaled, (canvas.getWidth() - bmp_width) / 2, (canvas.getHeight() - bmp_height) / 5, null);
        }
    }

    @Override
    public void setAlpha(int alpha) {
        paint.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        paint.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return paint.getAlpha();
    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        computeHex(bounds);
        invalidateSelf();
    }

    public void setIcon(int drawable){
        Bitmap bmp_original = BitmapFactory.decodeResource(context.getResources(), drawable);
        bmp_scaled = Bitmap.createScaledBitmap(bmp_original, bmp_width, bmp_height, false);
    }

    public void computeHex(Rect bounds) {

        final int width = bounds.width();
        final int height = (int) (bounds.height() * 0.7);
        final int size = Math.min(width, height);
        final int centerX = bounds.left + (width / 2);
        final int centerY = bounds.top + (height / 2);

        p_large.reset();

        p_large.addPath(createHexagon(size, centerX, centerY));
        p_small.addPath(createHexagon((int) (size * 0.9), centerX, centerY));
    }

    private Path createHexagon(int size, int centerX, int centerY) {
        final float section = (float) (2.0 * Math.PI / numberOfSides);
        int radius = size / 2;
        Path polygonPath = temporal;
        polygonPath.reset();
        polygonPath.moveTo((float) (centerX + radius * Math.cos(0)),
                (float) (centerY + radius * Math.sin(0)));

        for (int i = 1; i < numberOfSides; i++) {
            polygonPath.lineTo((float)(centerX + radius * Math.cos(section * i)),
                    (float)(centerY + radius * Math.sin(section * i)));
        }

        polygonPath.close();
        return polygonPath;
    }
}
