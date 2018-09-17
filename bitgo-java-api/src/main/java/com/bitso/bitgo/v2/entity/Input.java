package com.bitso.bitgo.v2.entity;

import com.bitso.bitgo.util.DataScrub;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;

import java.math.BigDecimal;

/**
 * From https://www.bitgo.com/api/v2/#get-wallet-transaction
 *
 * @author kushal256
 * Date: 8/13/18
 */
@Data
public class Input {

    @JsonSerialize(using = DataScrub.HashColonSerializer.class)
    private String id;

    @JsonSerialize(using = DataScrub.StringSerializer.class)
    private String address;

    private BigDecimal value;
    //private String valueString;  //Not needed for now
    private String wallet;
    private int chain;
    private int index;
//    private String redeemScript;  //Not needed for now

    @JsonProperty("isSegwit")  //Jackson tries to remove 'is'
    private boolean isSegwit;

}
