package cn.waikey.touchinvalidwithnoscrollgridview.components;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 *  @author Created by Tsang
 * 实现item click 功能的ViewHolder, 与ItemClickAdapter配套使用。
 * @param <T> 适配器item的数据类型
 *
 */
public class ItemClickViewHolder<T> extends RecyclerView.ViewHolder implements View.OnClickListener{

    private T data;
    private OnItemClickListener<T> mListener;

    public ItemClickViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(mListener!=null){
            mListener.onItemClick(this, v, data);
        }
    }

    public void setOnItemClickListener(OnItemClickListener<T> listener){
        mListener = listener;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    /**
     * item click监听器，与ItemClickAdapter配套使用。
     * @param <E> 适配器item的数据类型
     */
    public interface OnItemClickListener<E>{
        void onItemClick(ItemClickViewHolder<E> vh, View view, E data);
    }
}
