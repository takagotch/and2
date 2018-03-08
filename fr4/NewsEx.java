private final static String URL ="http://news.yahoo.co.jp/pickup/computer/rss.xml";

private void loadItems(String xml){
  try{
    this.items.clear();
    XmlPullParser parser = Xml.newPullParser();
    parser.setInput(new StringReader(xml));
    int type = parser.getEventType();
    String tagName = null;
    NewsItem item = null;
    while(){}
  }
}


