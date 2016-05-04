package cn.waikey.touchinvalidwithnoscrollgridview.components;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

/**
 * 实现item click 功能的适配器，与 ItemClickViewHolder 和 OnItemClickListener 配合使用
 *
 * @author Created by Tsang
 * @param <E> 适配器item的数据类型
 * @param <VH>
 * @see ItemClickViewHolder
 * @see ItemClickViewHolder.OnItemClickListener
 */
public abstract class ItemClickAdapter<E, VH extends ItemClickViewHolder<E>>
        extends RecyclerView.Adapter<VH> {

    private ItemClickViewHolder.OnItemClickListener<E> mListener;

    public ItemClickAdapter(ItemClickViewHolder.OnItemClickListener<E> listener) {
        mListener = listener;
    }

    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        VH vh = onCreateItemClickViewHolder(parent, viewType);
        vh.setOnItemClickListener(mListener);
        return vh;
    }

    protected abstract VH onCreateItemClickViewHolder(ViewGroup parent, int viewType);

    @Override
    public void onBindViewHolder(VH holder, int position) {
        holder.setData( getItem(holder, position) );
        onBindItemClickViewHolder(holder, position);
    }

    protected abstract void onBindItemClickViewHolder(VH holder, int position);

    @Override
    public abstract int getItemCount();

    public abstract E getItem(VH holder, int position);

}
