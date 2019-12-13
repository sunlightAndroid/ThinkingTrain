package me.sunlight.sdk.common.widget.glide;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 
 * Glide 圆形图片 Transform
 */

public class GlideCircleTransform extends BitmapTransformation {
    public GlideCircleTransform(Context context) {
        super(context);
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        return circleCrop(pool, toTransform);
    }

    private static Bitmap circleCrop(BitmapPool pool, Bitmap source) {
        if (source == null) return null;
        int size = Math.min(source.getWidth(), source.getHeight());
        int x = (source.getWidth() - size) / 2;
        int y = (source.getHeight() - size) / 2;
        Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
        Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        }
        Canvas canvas = new Canvas(result);
        Paint paint = new Paint();
        paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
        paint.setAntiAlias(true);
        float r = size / 2f;
        canvas.drawCircle(r, r, r, paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}

//    //加载圆角图片
//    //加载圆角图片
//    public static void loadRoundImage(Context context, String url, final ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .placeholder(placeholder)
//                .error(placeholder)
//                //.centerCrop() 千万不要加，加了就没有圆角效果了
//                .transform(new CenterCrop(context), new GlideRoundTransform(context,10))
//                .into(imageView);
//    }
//
//
//    //加载圆形图片
//    public static void loadCirclePic(final Context context, String url, final ImageView imageView) {
//        Glide.with(context)
//                .load(url)
//                .asBitmap()
//                .placeholder(placeholder)
//                .error(placeholder)
//                .diskCacheStrategy(DiskCacheStrategy.ALL) //设置缓存
//                .into(new BitmapImageViewTarget(imageView) {
//                    @Override
//                    protected void setResource(Bitmap resource) {
//                        RoundedBitmapDrawable circularBitmapDrawable =
//                                RoundedBitmapDrawableFactory.create(context.getResources(), resource);
//                        circularBitmapDrawable.setCircular(true);
//                        imageView.setImageDrawable(circularBitmapDrawable);
//                    }
//                });
//
//    }