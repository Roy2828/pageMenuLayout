package com.roy.pagerlibrary.holder;

import android.view.View;

public interface PageMenuViewHolderCreator {

    AbstractHolder createHolder(View itemView);

    int getLayoutId();
}
