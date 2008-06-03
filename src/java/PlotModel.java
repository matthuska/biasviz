
interface PlotModel {
    void addView(IView view);
    void removeView(IView view);
    CoreModel getCoreModel();
    void setCoreModel(CoreModel m);
}
