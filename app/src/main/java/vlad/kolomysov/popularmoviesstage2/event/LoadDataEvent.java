package vlad.kolomysov.popularmoviesstage2.event;

public class LoadDataEvent
{
    public int mPage;

    public LoadDataEvent(int page) {
        this.mPage = page;
    }
}