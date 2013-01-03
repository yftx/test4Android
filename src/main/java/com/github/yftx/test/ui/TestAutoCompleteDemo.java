package com.github.yftx.test.ui;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.*;
import com.github.kevinsawicki.wishlist.ViewFinder;
import com.github.rtyley.android.sherlock.roboguice.activity.RoboSherlockActivity;
import com.github.yftx.test.R;
import com.github.yftx.test.utils.InfoUtils;
import com.github.yftx.test.utils.LogUtils;
import com.github.yftx.test.utils.task.GenericTask;
import com.github.yftx.test.utils.task.TaskParams;
import com.github.yftx.test.utils.task.TaskResult;
import com.google.common.collect.Lists;
import com.google.inject.Inject;
import roboguice.inject.ContentView;
import roboguice.inject.InjectView;
import roboguice.util.RoboAsyncTask;

import java.util.List;

/**
 * 演示自动提示输入框的使用
 *
 * @author yftx.net@gmail.com
 */


@ContentView(R.layout.auto_complete_activity)
public class TestAutoCompleteDemo extends RoboSherlockActivity {
    public static List<String> datas = Lists.newArrayList();

    static {
        datas.add("a");
        datas.add("ab");
        datas.add("abc");
        datas.add("abcd");
        datas.add("abcdef");
        datas.add("abcdefg");
        datas.add("abcdefgh");
        datas.add("abcdefghi");
        datas.add("abcdefghijk");
        datas.add("abcdefghijkl");
    }


    @InjectView(R.id.act_one)
    AutoCompleteTextView actOne;

    @InjectView(R.id.act_two)
    AutoCompleteTextView actTwo;

    CustomAutoCompleteAdapter customAutoCompleteAdapter;
    @Inject
    InputMethodManager imm;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //避免在AsyncTask 中提示sending message to a Handler on a dead thread 警告
        //bug of AsyncTask
        //link:　http://stackoverflow.com/questions/7320036/asynctask-in-broadcastreceiver-in-android-gives-sending-message-to-a-handler-on
        try {
            Class.forName("android.os.AsyncTask");
        } catch (ClassNotFoundException e) {
        }
//        代码获取InputMethodManager的方式
//        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);


        //检索本地内容
        actOne.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line,
                datas));

        //检索网络上的内容
        customAutoCompleteAdapter = new CustomAutoCompleteAdapter(getLayoutInflater(), this);
        actTwo.setAdapter(customAutoCompleteAdapter);
        actTwo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (imm.isActive()) {
                    //隐藏软键盘，传入的windowToken需要为触发软件盘所在的view
                    imm.hideSoftInputFromWindow(actTwo.getWindowToken(), 0);
                }


                String itemContent = (String) parent.getAdapter().getItem(position);
                InfoUtils.showMsgShort(itemContent);
            }
        });
    }
}


class CustomAutoCompleteAdapter extends BaseAdapter implements Filterable {
    ViewFinder finder;
    CustomFilter customFilter;
    GetTipsTask getTipsTask;

    static class ViewHolder {
        TextView textView;
    }


    List<String> tempDatas = Lists.newArrayList();
    private final LayoutInflater inflater;

    CustomAutoCompleteAdapter(LayoutInflater inflater, Context context) {
        this.inflater = inflater;
    }

    public void freshAdapterData(List<String> datas) {
        tempDatas.clear();
        tempDatas.addAll(datas);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return tempDatas.size();
    }

    @Override
    public Object getItem(int position) {
        return tempDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        View view;
        if (convertView != null) {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        } else {
            view = inflater.inflate(R.layout.auto_complete_item, null);
            finder = new ViewFinder(view);
            holder = new ViewHolder();
            holder.textView = finder.find(R.id.tv_content);
            view.setTag(holder);
        }
        holder.textView.setText((String) getItem(position));

        return view;
    }

    @Override
    public Filter getFilter() {
        if (customFilter == null) {
            customFilter = new CustomFilter();
        }
        return customFilter;
    }

    private class CustomFilter extends Filter {

        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            LogUtils.debug("当前输入的字符为  -->  " + prefix.toString());
            if (getTipsTask != null && getTipsTask.getStatus().equals(AsyncTask.Status.RUNNING)) {
                getTipsTask.cancel(true);
            }
            getTipsTask = new GetTipsTask(prefix.toString());
            getTipsTask.execute();
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

        }
    }

    private class GetTipsTask extends GenericTask {
        String prefix = "";
        List<String> tempData = Lists.newArrayList();

        private GetTipsTask(String prefix) {
            this.prefix = prefix;
        }

        @Override
        protected TaskResult _doInBackground(TaskParams... params) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 5; i++) {
                tempData.add(prefix + i);
            }
            return TaskResult.OK;
        }

        @Override
        protected void onPostExecute(TaskResult result) {
            freshAdapterData(tempData);
        }
    }
}

