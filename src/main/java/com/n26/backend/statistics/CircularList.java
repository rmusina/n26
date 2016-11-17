package com.n26.backend.statistics;


import java.util.ArrayList;


class CircularList<E> extends ArrayList<E> {

    public E get(int index) {
        return super.get(index % size());
    }
}