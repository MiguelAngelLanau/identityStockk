
package stockkms.identity_stockk.common;

import com.google.gson.Gson;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author fsernafortea
 */
public class RESTstock implements Serializable {

        
    private String ticker;
    private long msBuy;        
    private double priceBuy     = 0.0;  //  price when it was bought
    private int numShares       = 0;
    private double priceSold    = 0.0;
    private long msSold         = 0;
    private double feeBuy       = 0.0;
    private double feeSold      = 0.0;
    
    public String getTicker() {
        return ticker;
    }

    /**
     * @param ticker the ticker to set
     */
    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    /**
     * @return the msBuy
     */
    public long getMsBuy() {
        return msBuy;
    }

    /**
     * @param msBuy the msBuy to set
     */
    public void setMsBuy(long msBuy) {
        this.msBuy = msBuy;
    }

    /**
     * @return the priceBuy
     */
    public double getPriceBuy() {
        return priceBuy;
    }

    /**
     * @param priceBuy the priceBuy to set
     */
    public void setPriceBuy(double priceBuy) {
        this.priceBuy = priceBuy;
    }

    /**
     * @return the numShares
     */
    public int getNumShares() {
        return numShares;
    }

    /**
     * @param numShares the numShares to set
     */
    public void setNumShares(int numShares) {
        this.numShares = numShares;
    }
/**
     * @return the priceSold
     */
    public double getPriceSold() {
        return priceSold;
    }

    /**
     * @param priceSold the priceSold to set
     */
    public void setPriceSold(double priceSold) {
        this.priceSold = priceSold;
    }

    /**
     * @return the msSold
     */
    public long getMsSold() {
        return msSold;
    }

    /**
     * @param msSold the msSold to set
     */
    public void setMsSold(long msSold) {
        this.msSold = msSold;
    }

    /**
     * @return the feeBuy
     */
    public double getFeeBuy() {
        return feeBuy;
    }

    /**
     * @param feeBuy the feeBuy to set
     */
    public void setFeeBuy(double feeBuy) {
        this.feeBuy = feeBuy;
    }

    /**
     * @return the feeSold
     */
    public double getFeeSold() {
        return feeSold;
    }

    /**
     * @param feeSold the feeSold to set
     */
    public void setFeeSold(double feeSold) {
        this.feeSold = feeSold;
    }


    
    public String toJson(){
        Gson gson = new Gson();
        return gson.toJson(this);
    }
    
}
