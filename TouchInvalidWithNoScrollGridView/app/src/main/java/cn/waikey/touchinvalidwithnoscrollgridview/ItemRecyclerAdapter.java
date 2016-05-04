package cn.waikey.touchinvalidwithnoscrollgridview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import cn.waikey.touchinvalidwithnoscrollgridview.components.ItemClickAdapter;
import cn.waikey.touchinvalidwithnoscrollgridview.components.ItemClickViewHolder;

/**
 * Created by Tsang on 16/5/4.
 * <String> 数据类型
 */
public class ItemRecyclerAdapter  extends ItemClickAdapter<String,ItemRecyclerAdapter.ItemRecyclerHolder<String>>{
    //数据源
    private String[] args;

    public ItemRecyclerAdapter(ItemClickViewHolder.OnItemClickListener<String> listener,String[] args) {
        super(listener);
        this.args = args;
    }

    @Override
    protected ItemRecyclerHolder<String> onCreateItemClickViewHolder(ViewGroup parent, int viewType) {
      // attachToRoot Whether the inflated hierarchy should be attached to
      // the root parameter? If false, root is only used to create the
      // correct subclass of LayoutParams for the root view in the XML.
      // inflate 必须加上  attachToRoot，否则会显示不全
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recyclerview,parent,false);
        return new ItemRecyclerHolder<>(itemView);
    }

    /**
     * 填充数据
     */
    @Override
    protected void onBindItemClickViewHolder(ItemRecyclerHolder<String> holder, int position) {
        String arg = getItem(holder, position);
        holder.mTextView.setText(arg);
    }

    @Override
    public int getItemCount() {
        return null == args ? 0 : args.length;
    }

    @Override
    public String getItem(ItemRecyclerHolder<String> holder, int position) {
        return args[position];
    }

    class ItemRecyclerHolder<String> extends ItemClickViewHolder<String>{

        private TextView mTextView;

        public ItemRecyclerHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.Item_textView);
        }
    }
}
