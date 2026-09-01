//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
void main() {
    Scanner in = new Scanner(System.in);
    String fName = "";
    fName = SafeInput.getNonZeroLenString(in, "Enter your first name");

    System.out.print("Hello" + ' ' + fName);
}
