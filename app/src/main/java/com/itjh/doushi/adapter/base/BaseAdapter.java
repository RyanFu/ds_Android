package com.itjh.doushi.adapter.base;

import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * User: Axl.Jacobs(axl.jacobs@gmail.com)
 * Date: 2016-03-22
 * Time: 00:20
 * FIXME
 */
public abstract class BaseAdapter<T,VH extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<VH> {
    private ArrayList<T> data = new ArrayList<>();


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
