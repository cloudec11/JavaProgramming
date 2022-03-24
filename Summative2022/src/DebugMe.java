public class DebugMe {
    public static void main(String[] args) {
        System.out.println(17.95-10);
        double changeDue = 17.95;
        final double NICKEL = 0.05, DIME = 0.1, QUARTER = 0.25, LOONIE = 1, TOONIE = 2, FIVEBILL = 5, TENBILL = 10, TWENTYBILL = 20, FIFTYBILL = 50;
        double modFifty, modTwenty, modTen, modFive, modTwo, modOne, modNick, modDime, modQuarter;
        final int COINSBILLS = 9;
        double[][] changeAmount = new double[COINSBILLS][2];
        if(FIFTYBILL<changeDue) {
            modFifty = changeDue % FIFTYBILL;
            modFifty = (changeDue - modFifty)/FIFTYBILL;
            changeDue -= modFifty * FIFTYBILL;

            changeAmount[0][1] = modFifty;
        }
        changeAmount[0][0] = FIFTYBILL;
        if(TWENTYBILL<changeDue) {
            modTwenty = changeDue % TWENTYBILL;
            modTwenty = (changeDue - modTwenty)/TWENTYBILL;
            changeDue -= modTwenty * TWENTYBILL;

            changeAmount[1][1] = modTwenty;
        }
        changeAmount[1][0] = TWENTYBILL;
        if(TENBILL<changeDue) {
            modTen = changeDue % TENBILL;
            modTen = (changeDue - modTen)/TENBILL;
            changeDue -= modTen * TENBILL;
            changeAmount[2][1] = modTen;
        }
        changeAmount[2][0] = TENBILL;
        if(FIVEBILL<changeDue) {
            modFive = changeDue % FIVEBILL;
            modFive = (changeDue - modFive)/FIVEBILL;
            changeDue -= modFive * FIVEBILL;

            changeAmount[3][1] = modFive;
        }
        changeAmount[3][0] = FIVEBILL;
        if(TOONIE<changeDue) {
            modTwo = changeDue % TOONIE;
            modTwo = (changeDue - modTwo)/TOONIE;
            changeDue -= modTwo * TOONIE;

            changeAmount[4][1] = modTwo;
        }
        changeAmount[4][0] = TOONIE;
        if(LOONIE<changeDue) {
            modOne = changeDue % LOONIE;
            modOne = (changeDue - modOne)/LOONIE;
            changeDue -= modOne * LOONIE;

            changeAmount[5][1] = modOne;
        }
        changeAmount[5][0] = LOONIE;
        if(QUARTER<changeDue) {
            modQuarter = changeDue % QUARTER;
            modQuarter = (changeDue - modQuarter)/QUARTER;
            changeDue -= modQuarter * QUARTER;

            changeAmount[6][1] = modQuarter;
        }
        changeAmount[6][0] = QUARTER;
        if(DIME<changeDue) {
            modDime = changeDue % DIME;
            modDime = (changeDue - modDime)/DIME;
            changeDue -= modDime * DIME;

            changeAmount[7][1] = modDime;
        }
        changeAmount[7][0] = DIME;
        if(NICKEL<changeDue) {
            modNick = changeDue % NICKEL;
            modNick = (changeDue - modNick)/NICKEL;
            changeDue-=modNick * NICKEL;
            if(changeDue!=0){
                modNick++;
            }
            changeAmount[8][1] = modNick;
        }
        changeAmount[8][0] = NICKEL;
        for(int i=0; i<changeAmount.length; i++){
            for(double j:changeAmount[i]){
                System.out.print(j+" ");
            }
            System.out.println();
        }
    }
}
