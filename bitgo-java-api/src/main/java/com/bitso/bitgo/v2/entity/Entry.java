package com.bitso.bitgo.v2.entity;

import com.bitso.bitgo.util.DataScrub;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

/**
 * From https://www.bitgo.com/api/v2/#get-wallet-transaction
 *
 * @author kushal256
 * Date: 8/13/18
 */
@Data
public class Entry {

    @JsonSerialize(using = DataScrub.StringSerializer.class)
    private String address;

    private int inputs;
    private int outputs;
    private long value;
//    private String valueString;  //Not needed

    private String wallet;
    private String coinName;

    @JsonProperty("isChange")  //Jackson tries 'ischange'
    private boolean isChange;
    @JsonProperty("isPayGo")  //Jackson tries 'payGo'
    private boolean isPayGo;
}
