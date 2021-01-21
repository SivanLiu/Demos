package com.example.rxandroid;

/**
 * "sid": "29601022",
 * "text": "哈哈哈原谅我不厚道的笑了",
 * "type": "video",
 * "thumbnail": "http://wimg.spriteapp.cn/picture/2019/0617/205fb4bc-90e3-11e9-96bf-1866daeb0df1_wpd.jpg",
 * "video": "http://wvideo.spriteapp.cn/video/2019/0617/205fb4bc-90e3-11e9-96bf-1866daeb0df1_wpd.mp4",
 * "images": null,
 * "up": "95",
 * "down": "6",
 * "forward": "1",
 * "comment": "8",
 * "uid": "20746551",
 * "name": "温柔尝尽了吗",
 * "header": "http://wimg.spriteapp.cn/profile/large/2019/03/26/5c99f6e152ea2_mini.jpg",
 * "top_comments_content": "人没事就万幸了，听话女士，以后不要碰车了，不管四个轮还是两个轮的",
 * "top_comments_voiceuri": "",
 * "top_comments_uid": "20782663",
 * "top_comments_name": "拾歡7XE",
 * "top_comments_header": "http://wx.qlogo.cn/mmopen/2EzJggZltBP54y2zB04fSVESdBB1DicpJuJSRhGAUUmvxvrypibvHOt3DpSqLUdic917kZzVppAbO7o45bI7jHhfOQDmLF4EBHt/0",
 * "passtime": "2019-06-19 02:51:01"
 */
public class JokenBean {
    private String sid;
    private String text;
    private String type;
    private String thumbnail;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    @Override
    public String toString() {
        return "JokenBean{" +
                "sid='" + sid + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", thumbnail='" + thumbnail + '\'' +
                '}';
    }
}