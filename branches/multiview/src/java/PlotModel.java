
interface PlotModel {
//    Alignment getAlignment();
//    String getSecondary();
//    String getSecondaryWithGaps();
//    boolean hasSecondary();
    void addView(IView view);
    void removeView(IView view);
    CoreModel getCoreModel();
    void setCoreModel(CoreModel m);
}
