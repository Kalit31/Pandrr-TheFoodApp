package com.example.foodapp.common;

import com.example.foodapp.models.Item;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Common
{

    public static final int VIEWTYPE_GROUP = 0;
    public static final int VIEWTYPE_ITEM = 1;

    private static List<String> counters_available = new ArrayList<>();

    public static ArrayList<Item> sortList(ArrayList<Item> items)
    {
        Collections.sort(items,new SortbyCounter());
        return items;
    }

    public static ArrayList<Item> addCounters(ArrayList<Item> list) {
        int i = 0;
        ArrayList<Item> customList = new ArrayList<>();
        Item firstItem = new Item();

        if(!list.isEmpty())
        {
            firstItem.setCounter(list.get(0).getCounter());

        firstItem.setViewType(VIEWTYPE_GROUP);
        counters_available.add(list.get(0).getCounter());
        customList.add(firstItem);

        for (; i < list.size() - 1; i++) {
            Item item = new Item();
            int cntr1 = Integer.parseInt(list.get(i).getCounter());
            int cntr2 = Integer.parseInt(list.get(i + 1).getCounter());
            if (cntr1 == cntr2) {
                list.get(i).setViewType(VIEWTYPE_ITEM);
                customList.add(list.get(i));
            } else {
                list.get(i).setViewType(VIEWTYPE_ITEM);
                customList.add(list.get(i));
                item.setCounter(String.valueOf(cntr2));
                item.setViewType(VIEWTYPE_GROUP);
                counters_available.add(String.valueOf(cntr2));
                customList.add(item);
            }
        }
        list.get(i).setViewType(VIEWTYPE_ITEM);
        customList.add(list.get(i));
        }
        return customList;
    }
    public static int findPositionByCounter(String counter, ArrayList<Item> items)
    {
        for(int i=0;i<items.size();i++)
        {
            if(items.get(i).getCounter().compareTo(counter) == 0)
                return i;
        }
        return -1;
    }
}

class SortbyCounter implements Comparator<Item>
{
    public int compare(Item a,Item b)
    {
        return Integer.parseInt(a.getCounter()) - Integer.parseInt(b.getCounter());
    }
}
