package com.z2xinyu.collection.samples;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.stream.Collectors;

/**
 * 洗牌发牌
 *
 * @author z-x-y
 */
public class ShuffleCards {

    private static final String[] CARD_COLORS = {"♦", "♠", "♥", "♣"};
    private static final String[] CARD_NUMBERS = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};

    private static final List<Integer> POKER_CARD_INDEX = new ArrayList<>();
    private static final Map<Integer, String> POKER_CARD_MAP = new HashMap<>();

    /**
     * 洗牌，保留3张底牌
     *
     * @return 清洗后的牌
     */
    public static Map<String, List<String>> shuffle() {
        // 花色和数字拼牌  54
        List<String> cards = new ArrayList<>();
        for (String color : CARD_COLORS) {
            for (String number : CARD_NUMBERS) {
                String card = color.concat(number);
                cards.add(card);
            }
        }
        cards.add("大王");
        cards.add("小王");

        // 洗牌，shuffle方法随机打乱牌
        Collections.shuffle(cards);

        Map<String, List<String>> cardResult = new HashMap<>();
        // 3个人
        List<String> player1 = new ArrayList<>();
        List<String> player2 = new ArrayList<>();
        List<String> player3 = new ArrayList<>();
        // 底牌
        ArrayList<String> holeCards = new ArrayList<>();

        cardResult.put("player1", player1);
        cardResult.put("player2", player2);
        cardResult.put("player3", player3);
        cardResult.put("holeCards", holeCards);

        for (int i = 0; i < cards.size(); i++) {
            // 留3张牌
            if (i >= cards.size() - 3) {
                holeCards.add(cards.get(i));
            } else if (i % 3 == 0) {
                player1.add(cards.get(i));
            } else if (i % 3 == 1) {
                player2.add(cards.get(i));
            } else {
                player3.add(cards.get(i));
            }
        }
        return cardResult;
    }

    public static Map<String, List<String>> shuffleWithOrder() {
        int index = 0;
        // 按照花色进行数字排序，如果想按照大小，就遍历numbers
        for (String color : CARD_COLORS) {
            for (String number : CARD_NUMBERS) {
                String card = color.concat(number);
                // 52张牌的编号
                POKER_CARD_INDEX.add(index);
                // 52张牌编号对应的牌
                POKER_CARD_MAP.put(index, card);
                index++;
            }
        }
        POKER_CARD_MAP.put(index, "小王");
        POKER_CARD_INDEX.add(index);
        index++;
        POKER_CARD_MAP.put(index, "大王");
        POKER_CARD_INDEX.add(index);

        // 洗牌
        Collections.shuffle(POKER_CARD_INDEX);

        HashMap<String, List<String>> shuffleCards = new HashMap<>();

        // treeSet可以根据自然顺序排序
        TreeSet<Integer> player1 = new TreeSet<>();
        TreeSet<Integer> player2 = new TreeSet<>();
        TreeSet<Integer> player3 = new TreeSet<>();
        TreeSet<Integer> holeCards = new TreeSet<>();

        for (int j = 0; j < POKER_CARD_INDEX.size(); j++) {
            if (j >= POKER_CARD_INDEX.size() - 3) {
                holeCards.add(POKER_CARD_INDEX.get(j));
            } else if (j % 3 == 0) {
                player1.add(POKER_CARD_INDEX.get(j));
            } else if (j % 3 == 1) {
                player2.add(POKER_CARD_INDEX.get(j));
            } else {
                player3.add(POKER_CARD_INDEX.get(j));
            }
        }

        lookCards("player1", player1, shuffleCards);
        lookCards("player2", player2, shuffleCards);
        lookCards("player3", player3, shuffleCards);
        lookCards("holeCards", holeCards, shuffleCards);
        return shuffleCards;
    }

    public static void lookCards(String player, TreeSet<Integer> cards, Map<String, List<String>> shuffleMap) {
        List<String> pokerCards = cards.stream().map(POKER_CARD_MAP::get).collect(Collectors.toList());
        shuffleMap.put(player, pokerCards);
    }
}
