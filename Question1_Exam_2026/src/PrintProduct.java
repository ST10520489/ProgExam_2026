public class PrintProduct extends Product{
    public PrintProduct(String[] products, int[][] sales) {
        super(products, sales);
    }
    public void printTotalSales() {
        System.out.println("TOTAL PRODUCT SALES: " + GetTotalProductSold());
    }
    public void printAverageSales(){
        System.out.printf("AVERAGE PRODUCT SALES: %.0f%n", GetAverageProductSold());
    }
    public void printMostPopularProduct(){
        int maxSingleMonth = 0;
        String popular = GetMostPopularProduct();

        for(int i = 0; i < products.length; i++){
            if(products[i].equals(popular)) {
                for(int j = 0; j < sales[i].length; j++){
                    if(sales[i][j] > maxSingleMonth) {
                        maxSingleMonth = sales[i][j];
                    }
                }
            }
        }
        System.out.println("MOST POPULAR PRODUCT: " + popular + "(" + maxSingleMonth + ")");
    }
    public void generateReport() {
        System.out.println("\nPRODUCT SALES REPORT - 2026");
        System.out.println("**********************************************************");
        printTotalSales();
        printAverageSales();
        printMostPopularProduct();
    }
}
