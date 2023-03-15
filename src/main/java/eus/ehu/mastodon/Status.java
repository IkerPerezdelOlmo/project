package eus.ehu.mastodon;

public class Status {
    public String id;
    public String created_at;
    public String in_reply_to_id;
    public String in_reply_to_account_id;
    public boolean sensitive;
    public String spoiler_text;
    public String visibility;
    public String language;
    public String url;
    public String content;
    public int replies_count;
    public int reblogs_count;
    public int favuorites_count;
    public String edited_at;
    public Boolean fauvorited;
    public Boolean reblogged;
    public Boolean muted;
    public Boolean bookmarked;
    public Status reblog;
    public Account account;

    @Override
    public String toString(){
        return "Status{"+
                "id=''"+ id + "\'" +
                ", created at='" + created_at + "\'" +
                ", content='" + content + "\'" +
                ", reblog=" + reblog +
                ", account=" + account +
                "}";
    }
}
