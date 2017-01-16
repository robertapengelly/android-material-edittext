package robertapengelly.support.widget;

import  android.content.Context;
import  android.content.res.ColorStateList;
import  android.content.res.Resources;
import  android.content.res.TypedArray;
import  android.graphics.BitmapFactory;
import  android.graphics.Color;
import  android.graphics.PorterDuff;
import  android.graphics.drawable.BitmapDrawable;
import  android.graphics.drawable.Drawable;
import  android.os.Build;
import  android.util.AttributeSet;
import  android.util.TypedValue;
import  android.widget.EditText;
import  android.widget.TextView;

import  java.lang.reflect.Field;

import  robertapengelly.support.graphics.drawable.GradientDrawable;
import  robertapengelly.support.graphics.drawable.InsetDrawable;
import  robertapengelly.support.graphics.drawable.LayerDrawable;
import  robertapengelly.support.graphics.drawable.RippleDrawable;
import  robertapengelly.support.materialedittext.R;

public class MaterialEditText extends EditText {

    public MaterialEditText(Context context) {
        super(context);
        
        init(context);
    
    }
    
    public MaterialEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        
        init(context);
    
    }
    
    public MaterialEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        
        init(context);
    
    }
    
    private void createBackground(Context context, Resources res) {
    
        int colorControlActivated = getColorFromAttrRes(R.attr.colorControlActivated, context);
        
        if (colorControlActivated == 0)
            if (Build.VERSION.SDK_INT >= 21)
                colorControlActivated = getColorFromAttrRes(android.R.attr.colorControlActivated, context);
        
        int colorControlNormal = getColorFromAttrRes(R.attr.colorControlNormal, context);
        
        if (colorControlNormal == 0)
            if (Build.VERSION.SDK_INT >= 21)
                colorControlNormal = getColorFromAttrRes(android.R.attr.colorControlNormal, context);
        
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, res.getDisplayMetrics());
        int stroke_mask = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 3, res.getDisplayMetrics());
        
        GradientDrawable gd_mask = new GradientDrawable();
        gd_mask.setColor(Color.TRANSPARENT);
        gd_mask.setPadding(0, padding, 0, padding);
        gd_mask.setStroke(stroke_mask, colorControlNormal);
        
        LayerDrawable ld_mask = new LayerDrawable(new Drawable[] {gd_mask});
        ld_mask.setLayerInset(0, -(stroke_mask * 2), -(stroke_mask * 2), -(stroke_mask * 2), 0);
        
        int stroke_none_mask = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 1, res.getDisplayMetrics());
        
        GradientDrawable gd_none_mask = new GradientDrawable();
        gd_none_mask.setColor(Color.TRANSPARENT);
        gd_none_mask.setStroke(stroke_none_mask, colorControlNormal);
        
        LayerDrawable ld_none_mask = new LayerDrawable(new Drawable[] {gd_none_mask});
        ld_none_mask.setLayerInset(0, -(stroke_none_mask * 2), -(stroke_none_mask * 2), -(stroke_none_mask * 2), 0);
        
        RippleDrawable rd = new RippleDrawable(ColorStateList.valueOf(colorControlActivated), ld_none_mask, ld_mask);
        InsetDrawable id = new InsetDrawable(rd, (int) res.getDimension(R.dimen.control_inset_material));
        
        if (Build.VERSION.SDK_INT >= 16)
            setBackground(id);
        else
            //noinspection deprecation
            setBackgroundDrawable(id);
    
    }
    
    private int getColorFromAttrRes(int attr, Context context) {
    
        TypedArray a = context.obtainStyledAttributes(new int[] {attr});
        
        try {
            return a.getColor(0, 0);
        } finally {
            a.recycle();
        }
    
    }
    
    private void init(Context context) {
    
        // we don't need to change anything on lollipop or newer as we are
        // replicating the material feel as much as we can.
        if (Build.VERSION.SDK_INT >= 21)
            return;
        
        createBackground(context, context.getResources());
        initSelectionHandle(context);
        
        int editTextColor = getColorFromAttrRes(R.attr.editTextColor, context);
        
        if (editTextColor == 0)
            if (Build.VERSION.SDK_INT >= 11)
                editTextColor = getColorFromAttrRes(android.R.attr.editTextColor, context);
        
        setTextColor(editTextColor);
        
        // on pre-honeycomb theres no way to change the cusor color so we can end here
        if (Build.VERSION.SDK_INT < 11)
            return;
        
        int colorControlActivated = getColorFromAttrRes(R.attr.colorControlActivated, context);
        
        if (colorControlActivated == 0)
            if (Build.VERSION.SDK_INT >= 21)
                colorControlActivated = getColorFromAttrRes(android.R.attr.colorControlActivated, context);
        
        int value = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1,
            context.getResources().getDisplayMetrics());
        
        GradientDrawable gd = new GradientDrawable();
        gd.setColor(colorControlActivated);
        gd.setSize(value, value);
        
        try {
        
            // Get the editor
            Field field = TextView.class.getDeclaredField("mEditor");
            field.setAccessible(true);
            Object editor = field.get(this);
            
            // Set the drawables
            field = editor.getClass().getDeclaredField("mCursorDrawable");
            field.setAccessible(true);
            field.set(editor, new Drawable[] {gd, gd});
        
        } catch (final Exception ignored) {}
    
    }
    
    private void initSelectionHandle(Context context) {
    
        int colorControlActivated = getColorFromAttrRes(R.attr.colorControlActivated, context);
        
        if (colorControlActivated == 0)
            if (Build.VERSION.SDK_INT >= 21)
                colorControlActivated = getColorFromAttrRes(android.R.attr.colorControlActivated, context);
        
        if (Build.VERSION.SDK_INT >= 11) {
        
            try {
            
                final Field fEditor = TextView.class.getDeclaredField("mEditor");
                fEditor.setAccessible(true);
                Object editor = fEditor.get(this);
                
                Field ignoreActionUpEventField = editor.getClass().getDeclaredField("mIgnoreActionUpEvent");
                ignoreActionUpEventField.setAccessible(true);
                
                final Field fSelectHandleLeft = editor.getClass().getDeclaredField("mSelectHandleLeft");
                final Field fSelectHandleCenter = editor.getClass().getDeclaredField("mSelectHandleCenter");
                final Field fSelectHandleRight = editor.getClass().getDeclaredField("mSelectHandleRight");
                
                fSelectHandleLeft.setAccessible(true);
                fSelectHandleCenter.setAccessible(true);
                fSelectHandleRight.setAccessible(true);
                
                BitmapDrawable leftHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_left_mtrl_alpha));
                leftHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleLeft.set(editor, leftHandle);
                
                BitmapDrawable middleHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_middle_mtrl_alpha));
                middleHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleCenter.set(editor, middleHandle);
                
                BitmapDrawable rightHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_right_mtrl_alpha));
                rightHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleRight.set(editor, rightHandle);
            
            } catch (final Exception ignored) {}
        
        } else {
        
            try {
            
                final Field fSelectHandleLeft = TextView.class.getDeclaredField("mSelectHandleLeft");
                final Field fSelectHandleCenter = TextView.class.getDeclaredField("mSelectHandleCenter");
                final Field fSelectHandleRight = TextView.class.getDeclaredField("mSelectHandleRight");
                
                fSelectHandleLeft.setAccessible(true);
                fSelectHandleCenter.setAccessible(true);
                fSelectHandleRight.setAccessible(true);
                
                BitmapDrawable leftHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_left_mtrl_alpha));
                leftHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleLeft.set(this, leftHandle);
                
                BitmapDrawable middleHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_middle_mtrl_alpha));
                middleHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleCenter.set(this, middleHandle);
                
                BitmapDrawable rightHandle = new BitmapDrawable(context.getResources(),
                    BitmapFactory.decodeResource(context.getResources(), R.drawable.text_select_handle_right_mtrl_alpha));
                rightHandle.setColorFilter(colorControlActivated, PorterDuff.Mode.SRC_IN);
                fSelectHandleRight.set(this, rightHandle);
            
            } catch (final Exception ignored) {}

        }
    
    }

}