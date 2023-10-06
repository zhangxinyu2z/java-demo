package com.z2xinyu.date;

import lombok.Data;
import lombok.experimental.Accessors;
import org.junit.Test;

import java.io.Serializable;
import java.util.*;
import java.util.function.BinaryOperator;
import java.util.stream.Collectors;

/**
 * @author zhangxinyu
 * @date 2023/7/3
 **/
public class DateTest {

    @Data
    @Accessors(chain = true)
    class ExhibitionIndustryCategoryVo implements Serializable {

        private static final long serialVersionUID = -6245602084803370782L;

        private String id;

        private String nameCn;

        private String nameEn;

    }




    @Data
    @Accessors(chain = true)
    public class PurchaserRes {
        private String purchaseId;

        private String companyId;
    }

    public String addPrefixToKey(String key) {
        return "getCompanyCdpIdByPurchaserId_" + key;
    }
    // 提取id
    public String extractIdFromKey(Map.Entry<String, String> entry) {
        String key = entry.getKey();
        String prefix = "getCompanyCdpIdByPurchaserId_";
        if (key.startsWith(prefix)) {
            return key.substring(prefix.length());
        } else {
            return key; // 如果没有匹配的前缀，则直接返回原始key
        }
    }


    @Test
    public void testCalculateDiffDate() {
        ArrayList<PurchaserRes> list = new ArrayList<>();
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("bcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("bcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("cdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("cdefg"));
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("cdefg"));
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("cdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("abcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("abcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("abcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1001").setCompanyId("abcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("bcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("bcdefg"));
        list.add(new PurchaserRes().setPurchaseId("1002").setCompanyId("bcdefg"));

        Map<String, String> hlk = list.stream()
            .collect(Collectors.toMap(PurchaserRes::getPurchaseId, PurchaserRes::getCompanyId,
                BinaryOperator.minBy(String::compareTo)));
        // Map<String, String> hlk = list.stream()
        //     .collect(Collectors.toMap(PurchaserRes::getPurchaseId, PurchaserRes::getCompanyId,
        //         (c1, c2) -> c1.compareTo(c2) <= 0 ? c1 : c2));
        // Map<String, String> hlk = list.stream()
        //     .collect(Collectors.toMap(
        //         PurchaserRes::getPurchaseId,
        //         PurchaserRes::getCompanyId,
        //         (c1, c2) -> c1.compareTo(c2)<=0? c1 : c2)
        //     ));




        List<String> purchaserIds = Arrays.asList("getCompanyCdpIdByPurchaserId_4141234","getCompanyCdpIdByPurchaserId_13412414");
        HashMap<String, String> resultMap = new HashMap<>();
        // 返回的key value一一对应，无则null
        List<String> newIds = Arrays.asList("getCompanyCdpIdByPurchaserId_197856", "getCompanyCdpIdByPurchaserId_31414",
            "getCompanyCdpIdByPurchaserId_4141234","getCompanyCdpIdByPurchaserId_13412414",
            "getCompanyCdpIdByPurchaserId_bnad");
        for (int i = 0; i < purchaserIds.size(); i++) {
            resultMap.put(purchaserIds.get(i), "ABc");
        }
        Map<String, String> emo =
            newIds.stream().collect(Collectors.toMap(k -> k, k -> resultMap.getOrDefault(k, "emo")));

        List<String> collect1 =
            resultMap.entrySet().stream().filter(entry -> Objects.isNull(entry.getValue())).map(this::extractIdFromKey)
                .collect(Collectors.toList());

        List<PurchaserRes> purchaserRes = new ArrayList<>();
        PurchaserRes a = new PurchaserRes();
        a.setPurchaseId("15789451671");
        a.setCompanyId("ADFAFASDFASFAFSDFAF");
        purchaserRes.add(a);
        PurchaserRes a1 = new PurchaserRes();
        a1.setPurchaseId("15789451671");
        a1.setCompanyId("bADFAFASDFASFAFSDFAF");
        purchaserRes.add(a1);
        PurchaserRes a2 = new PurchaserRes();
        a2.setPurchaseId("15789451671");
        a2.setCompanyId("cADFAFASDFASFAFSDFAF");
        purchaserRes.add(a2);
        PurchaserRes b = new PurchaserRes();
        b.setPurchaseId("16745613461");
        b.setCompanyId("BAAFDFOOIDISLEIEIW");
        purchaserRes.add(b);

        Map<String, String> collect = purchaserRes.stream().collect(
            Collectors.toMap(res ->addPrefixToKey(res.getPurchaseId()), PurchaserRes::getCompanyId,
                (companyId1, companyId2) -> companyId1));


        Date startDate = Calendar.getInstance().getTime();
        Date endDate = Calendar.getInstance().getTime();
        String diff = dateDiff(startDate, endDate);
        System.out.println(diff);
    }

    public String dateDiff(Date startTime, Date endTime) {
        long nd = 1000 * 24 * 60 * 60;// 一天的毫秒数
        long nh = 1000 * 60 * 60;// 一小时的毫秒数
        long nm = 1000 * 60;// 一分钟的毫秒数
        long ns = 1000;// 一秒钟的毫秒数
        long diff;
        long day = 0;
        long hour = 0;
        long min = 0;
        long sec = 0;
        // 获得两个时间的毫秒时间差异

        diff = endTime.getTime() - startTime.getTime();
        day = diff / nd;// 计算差多少天
        hour = diff % nd / nh + day * 24;// 计算差多少小时
        min = diff % nd % nh / nm + day * 24 * 60;// 计算差多少分钟
        sec = diff % nd % nh % nm / ns;// 计算差多少秒
        // 输出结果
        return day + "天" + (hour - day * 24) + "小时"
            + (min - day * 24 * 60) + "分钟" + sec + "秒";

    }
}
