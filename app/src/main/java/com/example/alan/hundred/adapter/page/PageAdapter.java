package com.example.alan.hundred.adapter.page;

import android.content.Context;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterViewFlipper;

import com.example.alan.hundred.R;
import com.example.alan.hundred.fragment.page.PageType;
import com.example.alan.hundred.info.PageInfo;

import java.util.List;

/**
 * Function :
 * Modify Date : 2018/1/10
 *
 * @Author : Alan
 * Issue : TODO
 * Whether Solve :
 */

public class PageAdapter extends RecyclerView.Adapter<PageAdapter.BaseViewHolder> {

    private List<PageInfo> pageInfoList;
    private LayoutInflater mInflater;

    private static final int TYPE_IMAGE = 0;
    private static final int TYPE_IMAGE_TEXT = 1;

    public PageAdapter(List<PageInfo> pageInfoList, Context context) {
        this.pageInfoList = pageInfoList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder vh = null;
        //判断type类型返回不同holder
        if (viewType == PageType.IMAGE.ordinal()){
            vh = new BaseViewHolderOne(mInflater.inflate(R.layout.item_page_image,null, false));
        }else if (viewType == PageType.FILLPER.ordinal()){
            vh = new BaseViewHolderTwo(mInflater.inflate(R.layout.item_page_viewfilipper, null, false));
        }else if (viewType == PageType.IMAGE_TEXT.ordinal()){
            vh = new BaseViewHolderThree(mInflater.inflate(R.layout.item_page_image_text, null, false));
        }

        return vh;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindHolder(pageInfoList.get(position));
    }

    @Override
    public int getItemCount() {
        return pageInfoList.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (pageInfoList.get(position).getType()){
            case 0:
                return 0;
            case 1:
                return PageType.IMAGE.ordinal();
            case 2:
                return PageType.IMAGE_TEXT.ordinal();
            default:
                break;
        }
        return 0;
    }

    abstract class BaseViewHolder extends RecyclerView.ViewHolder {


        public BaseViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void bindHolder(PageInfo data);
    }

    class BaseViewHolderOne extends BaseViewHolder {
        AppCompatImageView imageView;
        public BaseViewHolderOne(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_page_image);
        }

        @Override
        public void bindHolder(PageInfo data) {
            imageView.setImageBitmap(data.getBitmap());
        }
    }

    class BaseViewHolderTwo extends BaseViewHolder {
        private AdapterViewFlipper adapterViewFlipper;

        public BaseViewHolderTwo(View itemView) {
            super(itemView);
            adapterViewFlipper = itemView.findViewById(R.id.item_page_viewFlipper);
        }

        @Override
        public void bindHolder(PageInfo data) {

        }
    }

    class BaseViewHolderThree extends BaseViewHolder {
        AppCompatImageView imageView;
        AppCompatTextView textView;

        public BaseViewHolderThree(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.item_page_image);
            textView = itemView.findViewById(R.id.item_page_text);
        }

        @Override
        public void bindHolder(PageInfo data) {
            imageView.setImageBitmap(data.getBitmap());
            textView.setText(data.getTitle());
        }
    }
}
