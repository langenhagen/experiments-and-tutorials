package org.sercho.masp.models.channel.api;

public abstract class GraphicalOutputChannelAPITest {
    //
    // public static class TestDataGenerator {
    //
    // @SuppressWarnings("unchecked")
    // public static Collection generate(final Class class1) {
    // final Collection back = new ArrayList();
    // if(class1 == String.class) {
    // back.add(null);
    // back.add("");
    // back.add("AABbcc");
    // back.add("Y");
    // back.add("/*(-!+|");
    // return back;
    // } else if(class1 == int.class) {
    // for(int i = -2; i < 3; i++) {
    // back.add(i);
    // }
    // return back;
    // } else if(class1 == GraphicalOutputChannelAPI.class) {
    // back.add(new HTMLGraphicalOutputChannel(mock(AjaxOutputAPI.class)));
    // // back.add(new SwingGraphicalOutputChannel());
    // return back;
    // } else if(class1 == TwoDimensionalCallback.class) {
    // back.add(null);
    // return back;
    // }
    // throw new IllegalStateException();
    // }
    // }
    //
    // public class CrossDataSets implements Iterable<Vector<Object>> {
    //
    // private Class[] classes;
    //
    // private CrossDataSets() {
    // }
    //
    // public CrossDataSets(final Class[] newClasses) {
    // this.classes = newClasses;
    // }
    //
    // public Iterator<Vector<Object>> iterator() {
    // return new Iterator<Vector<Object>>() {
    //
    // Iterator[] its = new Iterator[CrossDataSets.this.classes.length];
    //
    // int step = 0;
    //
    // long count = 0;
    //
    // Vector<Object> current = new Vector<Object>();
    //
    // public Iterator<Vector<Object>> init() {
    // for(int i = 0; i < CrossDataSets.this.classes.length; i++) {
    // this.current.add(new Object());
    // }
    //
    // this.its[0] =
    // TestDataGenerator.generate(CrossDataSets.this.classes[0]).iterator();
    // return this;
    // }
    //
    // public boolean hasNext() {
    // for(int i = 0; i < CrossDataSets.this.classes.length - 1; i++) {
    // if(this.its[i].hasNext()) {
    // return true;
    // }
    // }
    // System.out.println(this.count);
    // return false;
    // }
    //
    // public Vector<Object> next() {
    // this.count++;
    // Vector<Object> back = null;
    // while(back == null) {
    // // if hasNext
    // //
    // System.out.println("GraphicalOutputChannelAPITest.PairedDataSets.iterator().new Iterator<Vector<Object>>() {...}.next()"
    // // + step);
    // if(this.its[this.step].hasNext()) {
    // // get next element
    // this.current.set(this.step, this.its[this.step].next());
    // // if last step
    // if(this.step == CrossDataSets.this.classes.length - 1) {
    // // add to collection
    // back = this.current;
    // // create new ArrayList
    // this.current = headCopy(this.current);
    // }
    // // else
    // else {
    // // one step up
    // this.step++;
    // // new iterator for new step
    // this.its[this.step] =
    // TestDataGenerator.generate(CrossDataSets.this.classes[this.step]).iterator();
    // }
    // }
    // // no next element
    // else {
    // // one step down
    // this.step--;
    // }
    // }
    // return back;
    // }
    //
    // public void remove() {
    // throw new IllegalStateException();
    // }
    // }.init();
    // }
    //
    // }
    //
    // public class SingleDataSets implements Iterable<Vector<Object>> {
    //
    // private Class[] classes;
    //
    // private SingleDataSets() {
    // }
    //
    // public SingleDataSets(final Class[] newClasses) {
    // this.classes = newClasses;
    // }
    //
    // public Iterator<Vector<Object>> iterator() {
    // return new Iterator<Vector<Object>>() {
    //
    // Iterator[] its = new Iterator[SingleDataSets.this.classes.length];
    //
    // int step = 0;
    //
    // long count = 0;
    //
    // Vector<Object> current = new Vector<Object>();
    //
    // public Iterator<Vector<Object>> init() {
    // for(int i = 0; i < SingleDataSets.this.classes.length; i++) {
    // this.current.add(new Object());
    // this.its[i] =
    // TestDataGenerator.generate(SingleDataSets.this.classes[i]).iterator();
    // if(i != 0) {
    // this.current.set(i, this.its[i].next());
    // }
    // }
    //
    // return this;
    // }
    //
    // public boolean hasNext() {
    // if((this.step == SingleDataSets.this.classes.length - 1) &&
    // (!this.its[this.step].hasNext())) {
    // System.out.println(this.count);
    // return false;
    // }
    // return true;
    // }
    //
    // public Vector<Object> next() {
    // this.count++;
    //
    // Vector<Object> back = null;
    // while(back == null) {
    // // if next
    // if(this.its[this.step].hasNext()) {
    // // set next in vector
    // this.current.set(this.step, this.its[this.step].next());
    // // return vector
    // back = this.current;
    // }
    // // else
    // else {
    // // step up
    // this.step++;
    // }
    // }
    // return back;
    // }
    //
    // public void remove() {
    // throw new IllegalStateException();
    // }
    // }.init();
    // }
    //
    // }
    //
    // public class PairedDataSets implements Iterable<Vector<Object>> {
    //
    // private Class[] classes;
    //
    // private PairedDataSets() {
    // }
    //
    // public PairedDataSets(final Class[] newClasses) {
    // this.classes = newClasses;
    // }
    //
    // public Iterator<Vector<Object>> iterator() {
    // return new Iterator<Vector<Object>>() {
    //
    // Iterator[] its = new Iterator[PairedDataSets.this.classes.length];
    //
    // int stepBase = 0;
    //
    // int step = 1;
    //
    // long count = 0;
    //
    // Vector<Object> current = new Vector<Object>();
    //
    // public Iterator<Vector<Object>> init() {
    // for(int i = 0; i < PairedDataSets.this.classes.length; i++) {
    // this.its[i] =
    // TestDataGenerator.generate(PairedDataSets.this.classes[i]).iterator();
    // this.current.add(this.its[i].next());
    // }
    //
    // return this;
    // }
    //
    // public boolean hasNext() {
    // /*
    // * if ((stepBase == classes.length -1) &&
    // * (!its[stepBase].hasNext())) { System.out.println(count);
    // * return false; }
    // */
    // for(int i = this.stepBase + 1; i < PairedDataSets.this.classes.length -
    // 2; i++) {
    // if(this.its[i].hasNext()) {
    // return true;
    // }
    // }
    // System.out.println(this.count);
    // return false;
    // }
    //
    // public Vector<Object> next() {
    // this.count++;
    //
    // final Vector<Object> back = null;
    // while(back == null) {
    // // if step.hasNext
    // if(this.its[this.step].hasNext()) {
    // // set current
    // this.current.set(this.step, this.its[this.step].next());
    // break;
    // }
    // // step on the top
    // else if(this.step == PairedDataSets.this.classes.length - 1) {
    // // new iterator
    // this.its[this.step] =
    // TestDataGenerator.generate(PairedDataSets.this.classes[this.step]).iterator();
    // // while !stepBase.hasNext
    // while(!this.its[this.stepBase].hasNext()) {
    // // stepBase up
    // this.stepBase++;
    // }
    // // step = stepBase +1
    // this.step = this.stepBase + 1;
    // // set current
    // this.current.set(this.stepBase, this.its[this.stepBase].next());
    // this.current.set(this.step, this.its[this.step].next());
    // break;
    // }
    // // not on top
    // else {
    // // new iterator
    // this.its[this.step] =
    // TestDataGenerator.generate(PairedDataSets.this.classes[this.step]).iterator();
    // // step up
    // this.step++;
    // }
    // }
    // return this.current;
    // }
    //
    // public void remove() {
    // throw new IllegalStateException();
    // }
    // }.init();
    // }
    //
    // }
    //
    // private GraphicalOutputChannelAPI subject;
    //
    // private String string1, string2, string3, string5, string4;
    //
    // private int int1, int2, int3, int4, int5;
    //
    // private TwoDimensionalCallback tdc;
    //
    // protected class ReflectiveTestCaseWrapper<T> extends
    // ReflectiveTestCase<T> {
    //
    // public ReflectiveTestCaseWrapper(final T object,
    // final Class<? super T> testClass,
    // final Class<? extends RuntimeException> allowedUncheckedException,
    // final boolean testConstructors) {
    // super(object, testClass, allowedUncheckedException, testConstructors);
    // // TODO Auto-generated constructor stub
    // }
    //
    // public final void NullParametersInMethods() {
    // super.testNullParametersInMethods();
    // }
    //
    // public final void NullParametersInConstructors() {
    // super.testNullParametersInConstructors();
    // }
    //
    // }
    //
    // ReflectiveTestCaseWrapper<GraphicalOutputChannelAPI> reflectiveWrapper;
    //
    // protected GraphicalOutputChannelAPITest(final GraphicalOutputChannelAPI
    // object) {
    // this.reflectiveWrapper = new
    // ReflectiveTestCaseWrapper<GraphicalOutputChannelAPI>(object,
    // GraphicalOutputChannelAPI.class, IllegalArgumentException.class, true);
    // }
    //
    // @Ignore
    // public void NullParametersInMethods() {
    // this.reflectiveWrapper.NullParametersInMethods();
    // }
    //
    // @Ignore
    // public void NullParametersInConstructors() {
    // this.reflectiveWrapper.NullParametersInConstructors();
    // }
    //
    // @Test
    // public void newPointingXParameterTest() {
    // System.out.println("GraphicalOutputChannelAPITest.newPointingXParameterTest()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, int.class})) {
    // GraphicalOutputChannelAPIAxioms.newPointingXParameters((GraphicalOutputChannelAPI)a.get(0),
    // (Integer)a.get(1));
    // }
    // }
    //
    // @Test
    // public void newPointingYParameterTest() {
    // System.out.println("GraphicalOutputChannelAPITest.newPointingYParameterTest()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, int.class})) {
    // GraphicalOutputChannelAPIAxioms.newPointingYParameters((GraphicalOutputChannelAPI)a.get(0),
    // (Integer)a.get(1));
    // }
    // }
    //
    // @Test
    // public void newHeightParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.newHeightParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, int.class})) {
    // GraphicalOutputChannelAPIAxioms.newHeightParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (Integer)a.get(2));
    // }
    // }
    //
    // @Test
    // public void newWidthParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.newWidthParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, int.class})) {
    // GraphicalOutputChannelAPIAxioms.newWidthParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (Integer)a.get(2));
    // }
    // }
    //
    // @Test
    // public void newTextParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.newTextParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.newTextParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2));
    // }
    // }
    //
    // @Test
    // public void newURLParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.newURLParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.newURLParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2));
    // }
    // }
    //
    // @Test
    // public void addButtonParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.addButtonParameters()");
    // for(final Vector<Object> a : new SingleDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class, int.class,
    // int.class, int.class, int.class, int.class, String.class})) {
    // /*
    // * for (int i = 0; i < a.size(); i++) { if (a.get(i) == null) {
    // * System.out.println("null"); } else {
    // * System.out.println(a.get(i).getClass()); } }
    // */
    // GraphicalOutputChannelAPIAxioms.addButtonParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2), (Integer)a.get(3), (Integer)a.get(4),
    // (Integer)a.get(5), (Integer)a.get(6), (Integer)a.get(7),
    // (String)a.get(8));
    // }
    // }
    //
    // @Test
    // public void addImageParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.addImageParameters()");
    // for(final Vector<Object> a : new SingleDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class,
    // String.class, int.class, int.class, int.class, int.class, int.class})) {
    // GraphicalOutputChannelAPIAxioms.addImageParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2), (String)a.get(3), (Integer)a.get(4),
    // (Integer)a.get(5), (Integer)a.get(6), (Integer)a.get(7),
    // (Integer)a.get(8));
    // }
    // }
    //
    // @Test
    // public void addTextLabelParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.addTextLabelParameters()");
    // for(final Vector<Object> a : new SingleDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class, int.class,
    // int.class, int.class, int.class, int.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.addTextLabelParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2), (Integer)a.get(3), (Integer)a.get(4),
    // (Integer)a.get(5), (Integer)a.get(6), (Integer)a.get(7),
    // (String)a.get(8));
    // }
    // }
    //
    // @Test
    // public void removeLookAndFeelParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.removeLookAndFeelParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.removeLookAndFeelParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1));
    // }
    // }
    //
    // @Test
    // public void setCallbackParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.setCallbackParameters()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.setCallbackParameters((GraphicalOutputChannelAPI)a.get(0),
    // (TwoDimensionalCallback)a.get(1));
    // }
    // }
    //
    // @Test
    // public void setLookAndFeelParameters() {
    // System.out.println("GraphicalOutputChannelAPITest.setLookAndFeelParameters()");
    // for(final Vector<Object> a : new SingleDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class,
    // String.class, String.class, int.class, String.class, String.class,
    // int.class})) {
    // GraphicalOutputChannelAPIAxioms.setLookAndFeelParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2), (String)a.get(3), (String)a.get(4),
    // (Integer)a.get(5), (String)a.get(6), (String)a.get(7),
    // (Integer)a.get(8));
    // }
    // }
    //
    // @Test
    // public void setLookAndFeelOfElement() {
    // System.out.println("GraphicalOutputChannelAPITest.setLookAndFeelOfElement()");
    // for(final Vector<Object> a : new CrossDataSets(new Class[]{
    // GraphicalOutputChannelAPI.class, String.class, String.class})) {
    // GraphicalOutputChannelAPIAxioms.setLookAndFeelOfElementParameters((GraphicalOutputChannelAPI)a.get(0),
    // (String)a.get(1), (String)a.get(2));
    // }
    // }
    //
    // protected static ArrayList<Object> headCopy(final ArrayList<Object> in) {
    // final ArrayList<Object> back = new ArrayList<Object>();
    // for(int i = 0; i < in.size(); i++) {
    // back.add(new Object());
    // }
    //
    // for(int i = 0; i < in.size() - 1; i++) {
    // back.set(i, in.get(i));
    // }
    //
    // return back;
    // }
    //
    // protected static Vector<Object> headCopy(final Vector<Object> in) {
    // final Vector<Object> back = new Vector<Object>();
    // for(int i = 0; i < in.size(); i++) {
    // back.add(new Object());
    // }
    //
    // for(int i = 0; i < in.size() - 1; i++) {
    // back.set(i, in.get(i));
    // }
    //
    // return back;
    // }
    //
    // protected static int resolveIndex(final int[] map, int relativeI) {
    // for(int i = 0; i < map.length; i++) {
    // relativeI -= map[i];
    // if(relativeI < 0) {
    // return i;
    // }
    // }
    // throw new IllegalArgumentException("Index is not in list!");
    // }
}
