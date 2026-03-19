public class NoMultipleUnaryCompliant {
    public void singleNegation(Boolean flag) {
        Boolean result = !flag;
        if (!result) {
            System.debug('negated');
        }
    }

    public void normalIncrement() {
        Integer i = 0;
        i++;
        ++i;
        Integer[] arr = new Integer[]{1, 2, 3};
        Integer x = arr[i++];
    }

    public void normalDecrement() {
        Integer j = 10;
        j--;
        --j;
    }

    public void doubleNotInsideString() {
        String s = 'This has !! inside it';
        System.debug(s);
    }
}
