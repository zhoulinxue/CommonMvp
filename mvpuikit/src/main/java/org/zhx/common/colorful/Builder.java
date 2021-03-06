package org.zhx.common.colorful;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.ColorRes;

import java.util.regex.Pattern;

/**
 * @ProjectName: colorfullText
 * @Package: org.zhx.common.colorfull
 * @ClassName: Builder
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/8/15 17:24
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/8/15 17:24
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class Builder {

    /**
     * @Description:字段描述
     * @CreateDate: 需要构建的 文字
     */

    public String source = "";


    public Context context;
    /**
     * @Description:字段描述
     * @CreateDate:
     */

    public int pressedColor;
    /**
     * @Description:字段描述
     * @CreateDate: 本地图片
     */

    public int[] drawableSrc;
    /**
     * @Description:字段描述
     * @CreateDate: 图片位置
     */

    public int drawableIndex;

    /**
     * @Description:字段描述
     * @CreateDate: 构建目标
     */

    public String target;
    /**
     * @Description:字段描述
     * @CreateDate: 构建多个目标
     */

    public String[] targets;
    /**
     * @Description:字段描述
     * @CreateDate: 构建起始位置
     */

    public int start;
    /**
     * @Description:字段描述
     * @CreateDate: 构建 终止位置
     */

    public int end;
    /**
     * @Description:字段描述
     * @CreateDate: 字体颜色
     */

    public int textColor;

    /**
     * @Description:字段描述
     * @CreateDate: 字体大小
     */

    public float textSize;
    /**
     * @Description:字段描述
     * @CreateDate: 字体style
     */

    public int typefaces;

    /**
     * @Description:字段描述
     * @CreateDate: 字体背景
     */

    public int backgroundColor;
    /**
     * @Description:字段描述
     * @CreateDate: 是否中划线
     */

    public boolean isStrikethrough = false;

    /**
     * @Description:字段描述
     * @CreateDate: 是否 下划线
     */

    public boolean isUnderline = false;
    /**
     * @Description:字段描述
     * @CreateDate: 文字替换图片
     */

    public boolean isReplaceTarget = false;
    /**
     * @Description:字段描述
     * @CreateDate: 点击事件
     */

    public TargetClick click;
    /**
     * @Description:字段描述
     * @CreateDate:
     */

    public TextView textView;
    /**
     * @Description:字段描述
     * @CreateDate: placeHolder
     */

    private String place = "!";
    /**
     * @Description:字段描述
     * @CreateDate: 按正则表达式 替换
     */

    public Pattern patten;

    /**
     * @Description:字段描述
     * @CreateDate: 替换回调
     */

    public OnPatternFind find;


    public Builder isUnderline(boolean isUnderline) {
        this.isUnderline = isUnderline;
        return this;
    }

    public Builder(Context context) {
        this.context = context;
    }

    public Builder textColor(@ColorRes int color) {
        this.textColor = context.getResources().getColor(color);
        return this;
    }

    public Builder textSize(float textSize) {
        this.textSize = textSize;
        return this;
    }

    public Builder insertDrawableSrc(int place, int... src) {
        this.drawableSrc = src;
        this.drawableIndex = place;
        return this;
    }

    public Builder target(String target) {
        targets(target);
        return this;
    }

    public Builder targets(String... targets) {
        this.targets = targets;
        return this;
    }

    public Builder pattenStr(String patten, OnPatternFind find) {
        isReplaceTarget = true;
        this.patten = Pattern.compile(patten, Pattern.CASE_INSENSITIVE);
        this.find = find;
        return this;
    }

    public Builder textStyles(int types) {
        this.typefaces = types;
        return this;
    }

    public Builder isStrikethrough(boolean isStrikethrough) {
        this.isStrikethrough = isStrikethrough;
        return this;
    }

    public Builder backgroundColor(@ColorRes int color) {
        this.backgroundColor = color;
        return this;
    }


    public boolean hasTargets(String source, String[] targets) {
        if (targets == null || targets.length == 0) {
            Log.e("ColorfulText", "targets==null");
            return false;
        }
        for (String target : targets) {
            return hasTarget(source, target);
        }
        return false;
    }


    public boolean hasTarget(String source, String target) {
        Log.e("ColorfulText", source + ":" + target);
        boolean hasSource = !TextUtils.isEmpty(source);
        boolean hasTarget = hasSource && !TextUtils.isEmpty(target) && source.contains(target);
        Log.e("ColorfulText", hasTarget + "   hasSource=  " + hasSource);
        if (hasTarget) {
            start = source.indexOf(target);
            end = start + target.length();
        }
        return hasTarget || isPatten();
    }

    public boolean hasDrawable() {
        boolean hasDrawable = false;
        if (drawableSrc != null && drawableSrc.length > 0) {
            hasDrawable = true;
        }
        Log.e("source", hasDrawable ? "有图" : "没图");
        return hasDrawable && patten == null;
    }

    protected String resetSource(String source, int drawableIndex, int targetIndex) {
        String realTarget = targetIndex == -1 ? "" : targets[targetIndex];
        char[] chars = source.toCharArray();
        String[] newchars = new String[source.length() + drawableSrc.length];
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < newchars.length; i++) {
            if (i < drawableIndex) {
                newchars[i] = chars[i] + "";
            } else if (i >= drawableIndex && i < drawableIndex + drawableSrc.length) {
                newchars[i] = place;
                builder.append(newchars[i]);
            } else {
                newchars[i] = chars[i - drawableSrc.length] + "";
            }
            if (targetIndex != -1)
                targets[targetIndex] = builder.toString() + realTarget;
            else
                target(builder.toString());
            source = getNewSource(newchars);
        }
        return source;
    }

    public String getNewSource(String[] newchars) {
        StringBuilder builder = new StringBuilder();
        for (String str : newchars) {
            builder.append(str);
        }
        return builder.toString();
    }

    public Builder replaceDrawableSrc(int... drawable) {
        this.isReplaceTarget = true;
        this.drawableSrc = drawable;
        return this;
    }

    public Builder pressdColor(int pressedColor) {
        this.pressedColor = pressedColor;
        return this;
    }


    public Builder spanClick(TargetClick click) {
        this.click = click;
        return this;
    }


    public Builder source(String source) {
        this.source = source;
        return this;
    }


    private CharSequence build() {
        ColorfulText colorfullText = new ColorfulText();

        if (hasDrawable()) {
            if (!isReplaceTarget)
                source = resetSource(source, drawableIndex, -1);
            else
                source = resetSourcebyTarget(source);
        }

        colorfullText.init(source);
        if (!TextUtils.isEmpty(source)) {
            boolean hasTarget = hasTargets(source, targets);
            Log.e("ColorfulText", "hasTarget..." + hasTarget);
            if (hasTarget) {
                return colorfullText.build(this, false);
            } else if (isPatten()) {
                return colorfullText.build(this, true);
            }
        }
        return source;
    }

    private String resetSourcebyTarget(String source) {
        String newStr = source;
        for (int i = 0; i < targets.length; i++) {
            String target = targets[i];
            int index = source.indexOf(target);
            newStr = resetSource(newStr, index, i);
        }
        return newStr;
    }

    public void bind(TextView textView) {
        if (textView != null) {
            this.textView = textView;
            textView.setText(build());
        }
    }

    public boolean isPatten() {
        return patten != null;
    }
}

