package com.example.set;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;

public class CardView {
    int count, fill, shape, color;
    float x, y, width, height;

    public CardView(int count, int fill, int shape, int color) {
        this.count = count;
        this.fill = fill;
        this.shape = shape;
        this.color = color;
    }

    public void draw(Canvas canvas) {

        RectF contour = new RectF(x, y, x + width, y + height);
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);

        canvas.drawRect(contour, paint);

        Paint p = new Paint();
        p.setStyle(Paint.Style.STROKE);

        switch (color) {
            case 1:
                p.setColor(Color.RED);
                break;
            case 2:
                p.setColor(Color.rgb(0, 100, 0));
                break;
            case 3:
                p.setColor(Color.rgb(106, 90, 205));
                break;
        }

        switch (fill) {
            case 1:
                break;
            case 2:
                p.setStyle(Paint.Style.FILL);
                break;
            case 3:
                p.setStyle(Paint.Style.FILL_AND_STROKE);
                break;
        }

        if (count == 1) {
            switch (shape) {
                case 1:
                    canvas.drawOval(new RectF(x + width / 8, y + height / 2.3f, x + width - width / 8, y + height - height / 2.3f), p);
                    break;
                case 2:
                    canvas.drawCircle(x + width / 2, y + height / 2, width / 8, p);
//                float[] points = new float[]{x + width / 8, y + height / 2, x + width / 2, y + height / 2.5f,
//                        x + width / 2, y + height / 2.5f, x + width - width / 8, y + height / 2,
//                        x + width - width / 8, y + height / 2, x + width / 2, y + height - height / 2.5f,
//                        x + width / 2, y + height - height / 2.5f, x + width / 8, y + height / 2
//                };
//                canvas.drawLines(points, p);
                    break;
                case 3:
                    RectF rect11 = new RectF(x + width / 8, y + height / 3, x + width / 2, y + (height / 3) + (height / 5));
//                RectF rect21 = new RectF(rect11);
//                rect21.offsetTo(x+width/8, y + (height / 3) +(height / 12));
                    RectF rect12 = new RectF(x + width / 2, y + height / 3, x + width - width / 8, y + (height / 3) + (height / 5));
//                RectF rect22 = new RectF(rect12);
//                rect22.offsetTo(x + width / 2, y + (height / 3) +(height / 12));
//                    canvas.drawRect(rect11,p);
//                    canvas.drawRect(rect12,p);
//                    canvas.drawRect(rect21,p);
//                    canvas.drawRect(rect22,p);

                    canvas.drawArc(rect11, 0, -180, false, p);
//                canvas.drawArc(rect21, 0, -180, false, p);
                    canvas.drawArc(rect12, 0, 180, false, p);
//                canvas.drawArc(rect22, 0, 180, false, p);
                    break;
            }
        }

        if (count == 2) {
            switch (shape) {
                case 1:
                    canvas.drawOval(new RectF(x + width / 8, y + height / 4f, x + width - width / 8, y + height - 2*(height / 2.3f)), p);
                    canvas.drawOval(new RectF(x + width / 8, y + height / 2.3f, x + width - width / 8, y + height - height / 2.3f), p);
                    break;
                case 2:
                    canvas.drawCircle(x + width / 2, y + height / 4, width / 8, p);
                    canvas.drawCircle(x + width / 2, y + height / 2, width / 8, p);
                    break;
                case 3:
                    RectF rect11 = new RectF(x + width / 8, y + height / 5, x + width / 2, y + (height / 5) + (height / 5));
                    RectF rect12 = new RectF(x + width / 2, y + height / 5, x + width - width / 8, y + (height / 5) + (height / 5));
                    canvas.drawArc(rect11, 0, -180, false, p);
                    canvas.drawArc(rect12, 0, 180, false, p);
                    RectF rect21 = new RectF(x + width / 8, y + height / 3, x + width / 2, y + (height / 3) + (height / 5));
                    RectF rect22 = new RectF(x + width / 2, y + height / 3, x + width - width / 8, y + (height / 3) + (height / 5));
                    canvas.drawArc(rect21, 0, -180, false, p);
                    canvas.drawArc(rect22, 0, 180, false, p);
                    break;
            }
        }

        if (count == 3) {
            switch (shape) {
                case 1:
                    canvas.drawOval(new RectF(x + width / 8, y + height / 4f, x + width - width / 8, y + height - 2*(height / 2.3f)), p);
                    canvas.drawOval(new RectF(x + width / 8, y + height / 2.3f, x + width - width / 8, y + height - height / 2.3f), p);
                    canvas.drawOval(new RectF(x + width / 8, y + 3*(height / 4f), x + width - width / 8, y + height - (height / 2.9f)), p);
                    break;
                case 2:
                    canvas.drawCircle(x + width / 2, y + height / 4, width / 8, p);
                    canvas.drawCircle(x + width / 2, y + height / 2, width / 8, p);
                    canvas.drawCircle(x + width / 2, y + 3*(height / 4), width / 8, p);
                    break;
                case 3:
                    RectF rect11 = new RectF(x + width / 8, y + height / 5, x + width / 2, y + (height / 5) + (height / 5));
                    RectF rect12 = new RectF(x + width / 2, y + height / 5, x + width - width / 8, y + (height / 5) + (height / 5));
                    canvas.drawArc(rect11, 0, -180, false, p);
                    canvas.drawArc(rect12, 0, 180, false, p);
                    RectF rect21 = new RectF(x + width / 8, y + height / 3, x + width / 2, y + (height / 3) + (height / 5));
                    RectF rect22 = new RectF(x + width / 2, y + height / 3, x + width - width / 8, y + (height / 3) + (height / 5));
                    canvas.drawArc(rect21, 0, -180, false, p);
                    canvas.drawArc(rect22, 0, 180, false, p);
                    RectF rect31 = new RectF(x + width / 8, y + 4*(height / 5), x + width / 2, y + (height / 3) + (height / 5));
                    RectF rect32 = new RectF(x + width / 2, y + 4*(height / 5), x + width - width / 8, y + (height / 3) + (height / 5));
                    canvas.drawArc(rect31, 0, -180, false, p);
                    canvas.drawArc(rect32, 0, 180, false, p);
                    break;
            }
        }

        p.setTextSize(40);
        canvas.drawText(this.count + "" + this.fill + "" + this.shape + "" + this.color, this.x + this.width / 2, this.y + this.height / 6, p);
    }

    public boolean clickOnCard(float touch_x, float touch_y) {
        if (touch_x >= x && touch_x <= x + width && touch_y >= y && touch_y <= y + height) {
            return true;
        } else return false;
    }

}
