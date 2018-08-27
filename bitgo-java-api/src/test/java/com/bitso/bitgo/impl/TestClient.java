package com.bitso.bitgo.impl;

import com.bitso.bitgo.entity.Transaction;
import com.bitso.bitgo.entity.Wallet;
import com.bitso.bitgo.entity.WalletTransactionResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static junit.framework.TestCase.assertTrue;

/**
 * Simple client tests.
 *
 * @author Enrique Zamudio
 * Date: 5/8/17 8:04 PM
 */
@Slf4j
public class TestClient {

    public static final String WALLET_ID = "yourvalue";
    public static final String TOKEN = "yourvalue";
    private static final String COIN = "tbtc";
    private final BitGoClientImpl client = new BitGoClientImpl(TOKEN);

    //
//    @Test
//    public void testSendMany() throws IOException {
//        Map<String,BigDecimal> targets = new HashMap<>();
//        targets.put("[ADDRESS1]", new BigDecimal("0.001"));
//        targets.put("[ADDRESS2]", new BigDecimal("0.001"));
//        Optional<SendCoinsResponse> resp = client.sendMany(COIN, "wallet", "pass", targets, null,
//                "test", null, null, 1, true);
//        Assert.assertTrue(resp.isPresent());
//        Assert.assertNotNull(resp.get().getTx());
//    }
    public static long parseTimeInstant(String time) {
        return Instant.from(DateTimeFormatter.ISO_INSTANT.parse(time)).toEpochMilli(); // could be written f.parse(time, Instant::from);
    }

    @Before
    public void setup() {
        client.setBaseUrl("https://test.bitgo.com/api/v2");
    }

    @Test
    public void getWallets() throws IOException {
        List<Wallet> wallets = client.listWallets(COIN);
        Assert.assertNotNull(wallets);
        Assert.assertFalse(wallets.isEmpty());
    }

    @Test
    public void currentUserProfile() throws IOException {
        final Optional<Map<String, Object>> profile = client.getCurrentUserProfile();
        Assert.assertNotNull(profile.get());
    }

    @Test
    public void listWalletTransactions() throws IOException {
        final WalletTransactionResponse list = client.listWalletTransactions("tbtc", WALLET_ID, null);

        long priorTimestamp = Long.MAX_VALUE;
        for (Transaction txn : list.getTransactions()) {
            long currentTimestamp = parseTimeInstant(txn.getDate());
//            log.info("priorTimestamp = {} and current = {}", priorTimestamp, currentTimestamp);
            assertTrue(currentTimestamp < priorTimestamp);
            System.out.println("current = " + currentTimestamp);
            priorTimestamp = currentTimestamp;
        }
        assertTrue(list.getTransactions().size() > 10);
        System.out.println(list);
    }
}
