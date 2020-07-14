package org.zhx.common.mvp.demo.adapters;

import androidx.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import org.zhx.common.mvp.demo.R;

import java.util.List;

/**
 * @ProjectName: CommonMvp
 * @Package: org.zhx.common.mvp.demo.adapters
 * @ClassName: StringAdapter
 * @Description:java类作用描述
 * @Author: zhouxue
 * @CreateDate: 2020/7/14 16:52
 * @UpdateUser: 更新者
 * @UpdateDate: 2020/7/14 16:52
 * @UpdateRemark: 更新说明
 * @Version:1.0
 */
public class StringAdapter extends BaseQuickAdapter<String, BaseViewHolder> {
    public StringAdapter(@Nullable List<String> data) {
        super(R.layout.list_item_layout, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {
        helper.setText(R.id.item_tv, item);
    }
}
