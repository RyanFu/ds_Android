package com.itjh.doushi.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.LinkedList;
import java.util.List;

/**
 * User: Axl_Jacobs(Axl.Jacobs@gmail.com)
 * Date: 2016-03-01
 * Time: 17:39
 * FIXME
 */
public abstract class BaseQuickAdapter<T, H extends BaseAdapterHelper> extends RecyclerView.Adapter<BaseAdapterHelper> implements View.OnClickListener {
    protected static final String TAG = BaseQuickAdapter.class.getSimpleName();

    /**
     * 上下文
     */
    protected final Context context;

    /**
     * 布局ID
     */
    protected final int layoutResId;

    /**
     * 数据
     */
    protected final LinkedList<T> data;

    protected boolean displayIndeterminateProgress = false;

    private OnItemClickListener mOnItemClickListener = null;

    //define interface
    public interface OnItemClickListener<T> {
        void onItemClick(View view, int position, T object);
    }

    /**
     * 创建适配器
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     */
    public BaseQuickAdapter(Context context, int layoutResId) {
        this(context, layoutResId, null);
    }

    /**
     * 带初始参数的创建
     *
     * @param context     The context.
     * @param layoutResId The layout resource id of each item.
     * @param data        A new list is created out of this one to avoid mutable list
     */
    public BaseQuickAdapter(Context context, int layoutResId, LinkedList<T> data) {
        this.data = data == null ? new LinkedList<>() : data;
        this.context = context;
        this.layoutResId = layoutResId;
    }


    @Override
    public BaseAdapterHelper onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layoutResId, viewGroup, false);
        view.setOnClickListener(this);
        return new BaseAdapterHelper(view);
    }

    @Override
    public void onBindViewHolder(BaseAdapterHelper helper, int position) {
        helper.itemView.setTag(position);
        T item = getItem(position);
        convert((H) helper, item);
    }

    /**
     * Implement this method and use the helper to adapt the view to the given item.
     *
     * @param helper A fully initialized helper.
     * @param item   The item that needs to be displayed.
     */
    protected abstract void convert(H helper, T item);

    @Override
    public void onClick(View v) {
        if (mOnItemClickListener != null) {
            mOnItemClickListener.onItemClick(v, (int) v.getTag(), getItem((int) v.getTag()));
        }
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }


    /**
     * 追加数据
     *
     * @param list
     */
    public void appendToList(List<T> list) {
        if (list == null) {
            return;
        }
        data.addAll(list);
        notifyItemRangeInserted(getItemCount(), list.size());
    }

    /**
     * 根据position 获取单个对象
     */
    public T getItem(int position) {
        // TODO Auto-generated method stub
        if (position > data.size() - 1) {
            return null;
        }
        return data.get(position);
    }


    /**
     * 获取数量
     */
    @Override
    public int getItemCount() {
        // TODO Auto-generated method stub
        return data.size();
    }


    /**
     * 追加数据
     *
     * @param list
     */
    public void appendToTopList(List<T> list) {
        if (list == null) {
            return;
        }
        data.addAll(0, list);
        notifyDataSetChanged();
    }

    /**
     * 清空数据
     */
    public void clear() {
        int i = getItemCount();
        data.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取列表数据
     */
    public List<T> getList() {
        return data;
    }

}
