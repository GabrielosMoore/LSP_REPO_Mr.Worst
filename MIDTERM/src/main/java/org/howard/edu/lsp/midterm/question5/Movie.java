package org.howard.edu.lsp.midterm.question5;

public class Movie implements Streamable {
    private String title;

    public Movie(String title) {
        this.title = title;
    }

    @Override
    public void play() {
        System.out.println("Playing movie: " + title);
    }

    @Override
    public void pause() {
        System.out.println("Paused movie: " + title);
    }

    @Override
    public void stop() {
        System.out.println("Stopped movie: " + title);
    }

    public void rewind(int seconds) {
        System.out.println("Rewinding movie: " + title + " by " + seconds + " seconds");
    }
} 