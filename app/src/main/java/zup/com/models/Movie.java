package zup.com.models;

public class Movie {

    private String Title;
    private String Year;
    private String Released;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Awards;
    private String Poster;
    private String imdbRating;
    private String MovieDirectory;
    private byte[] MovieImage;

    public byte[] getMovieImage() {
        return MovieImage;
    }

    public void setMovieImage(byte[] movieImage) {
        MovieImage = movieImage;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        this.Poster = poster;
    }

    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    public String getMovieDirectory() {
        return MovieDirectory;
    }

    public void setMovieDirectory(String movieDirectory) {
        this.MovieDirectory = movieDirectory;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        this.Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        this.Year = year;
    }

    public String getReleased() {
        return Released;
    }

    public void setReleased(String released) {
        this.Released = released;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        this.Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        this.Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        this.Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        this.Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        this.Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        this.Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        this.Language = language;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        this.Awards = awards;
    }
}
