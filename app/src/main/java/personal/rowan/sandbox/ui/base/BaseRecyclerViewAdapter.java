package personal.rowan.sandbox.ui.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rowan Hall
 */

public abstract class BaseRecyclerViewAdapter<T>
        extends RecyclerView.Adapter<BaseViewHolder<T>> {

    private List<T> mData;

    private OnItemClickListener mItemClickListener;
    private OnLongClickListener mLongClickListener;
    private OnChildItemClickListener mChildItemClickListener;

    private boolean mContextMenuEnabled;
    private boolean mContextMenuSingleClick;
    private int mContextSelectedPosition;

    public BaseRecyclerViewAdapter() {
        this(new ArrayList<>());
    }

    public BaseRecyclerViewAdapter(List<T> data) {
        setData(data);
    }

    public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return buildViewHolder(viewType,
                LayoutInflater.from(parent.getContext()).inflate(getLayoutId(viewType), parent, false));
    }

    @Override
    public void onBindViewHolder(BaseViewHolder<T> viewHolder, int position) {
        assignClickListener(viewHolder);
        viewHolder.bindView(getItem(position));
    }

    private void assignClickListener(final BaseViewHolder<T> viewHolder) {
        viewHolder.itemView.setOnClickListener(v -> broadcastClick(viewHolder, viewHolder.getAdapterPosition()));
        viewHolder.itemView.setOnLongClickListener(v -> broadcastLongClick(viewHolder, viewHolder.getAdapterPosition()));
    }

    private void broadcastClick(BaseViewHolder<T> viewHolder, int position) {
        if(mContextMenuEnabled && mContextMenuSingleClick) {
            setContextSelectedPosition(position);
        }
        if(mItemClickListener != null) {
            if(!mItemClickListener.onItemClick(this, viewHolder, viewHolder.itemView, position)) {
                viewHolder.onItemClicked();
            }
        } else {
            viewHolder.onItemClicked();
        }
    }

    private boolean broadcastLongClick(BaseViewHolder<T> holder, int position) {
        if(mContextMenuEnabled) {
            setContextSelectedPosition(position);
        }
        return mLongClickListener != null && mLongClickListener.onLongClick(this, holder, holder.itemView, position);
    }

    public void broadcastChildClick(BaseViewHolder holder, int position, View childView) {
        if (mChildItemClickListener != null) {
            if (!mChildItemClickListener.onChildItemClick(this, holder, holder.itemView, position, childView)) {
                holder.onChildItemClicked(childView);
            }
        } else {
            holder.onChildItemClicked(childView);
        }
    }

    public T getItem(int position) {
        if(mData != null && mData.size() > position) {
            return mData.get(position);
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return mData != null ? mData.size() : 0;
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data == null) {
            data = new ArrayList<>();
        }

        mData = data;
        notifyDataSetChanged();
    }

    public void insertItem(T item) {
        mData.add(item);
        notifyItemInserted(getItemCount() - 1);
    }

    public void changeItemAtPosition(T item, int position) {
        mData.set(position, item);
        notifyItemChanged(position);
    }

    public void removeItemAtPosition(int position) {
        if(position >= 0 && position < getItemCount()) {
            mData.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void paginateData(List<T> data) {
        if(mData == null || mData.isEmpty()) {
            mData = data;
            notifyDataSetChanged();
        } else {
            int originalSize = getItemCount();
            mData = data;
            notifyItemRangeInserted(originalSize, data.size());
        }
    }

    public boolean isContextMenuEnabled() {
        return mContextMenuEnabled;
    }

    public void setContextMenuEnabled(boolean contextMenuEnabled) {
        this.mContextMenuEnabled = contextMenuEnabled;
        notifyDataSetChanged();
    }

    public boolean shouldContextMenuShowOnSingleClick() {
        return mContextMenuSingleClick;
    }

    public void setShouldContextMenuShowOnSingleClick(boolean contextMenuSingleClick) {
        mContextMenuSingleClick = contextMenuSingleClick;
        notifyDataSetChanged();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void setOnLongClickListener(OnLongClickListener listener) {
        mLongClickListener = listener;
    }

    public void setOnChildItemClickListener(OnChildItemClickListener listener) {
        mChildItemClickListener = listener;
    }

    private void setContextSelectedPosition(int position) {
        if(position >= 0 && position < getItemCount()) {
            mContextSelectedPosition = position;
        }
    }

    public int getContextSelectedPosition() {
        return mContextSelectedPosition;
    }

    protected abstract int getLayoutId(int viewType);

    protected abstract BaseViewHolder<T> buildViewHolder(int viewType, View view);

    public interface OnLongClickListener {
        boolean onLongClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position);
    }

    public interface OnItemClickListener {
        boolean onItemClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position);
    }

    public interface OnChildItemClickListener {
        boolean onChildItemClick(BaseRecyclerViewAdapter adapter, BaseViewHolder holder, View adapterView, int position, View childView);
    }

}
