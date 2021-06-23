package com.example.xiaojingshiping_app.imagepicker;

import java.util.List;

public class PickerConfig {

    private static PickerConfig sPickerConfig;

    public static PickerConfig getInstance(){
        if (sPickerConfig == null){
            sPickerConfig = new PickerConfig();
        }
        return sPickerConfig;
    }

    private int maxSelectedCount = 1;
    private OnImagesSelectedFinishedListener mOnImagesSelectedFinishedListener = null;

    public int getMaxSelectedCount() {
        return maxSelectedCount;
    }

    public void setMaxSelectedCount(int maxSelectedCount) {
        this.maxSelectedCount = maxSelectedCount;
    }

    public OnImagesSelectedFinishedListener getOnImagesSelectedFinishedListener(){
        return mOnImagesSelectedFinishedListener;
    }

    public void setOnImagesSelectedFinishedListener(OnImagesSelectedFinishedListener listener){
        this.mOnImagesSelectedFinishedListener = listener;
    }

    public interface OnImagesSelectedFinishedListener{
        void onSelectedFinished(List<ImageItem> result);
    }
}
