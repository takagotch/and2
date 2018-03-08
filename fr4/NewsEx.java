private final static String URL ="http://news.yahoo.co.jp/pickup/computer/rss.xml";

private void loadItems(String xml){
  try{
    this.items.clear();
    XmlPullParser parser = Xml.newPullParser();
    parser.setInput(new StringReader(xml));
    int type = parser.getEventType();
    String tagName = null;
    NewsItem item = null;
    while(type != XmlPullParser.END_DOCUMENT){
      String name = parser.getName();
      if(type == XmlPullParser.START_TAG){
        tagName = name;
	if(name.equals("item")){
	  item = new NewsItem();
	}
      } else if(type == XmlPullParser.END_TAG){
        tagName = null;
	if(name.equals("item")){
	  this.items.add(item);
	  item = null;
	}
      } else if(type == XmlPullParser.TEXT){
        if(item != null && tagName != NULL){
	  if(tagName.equals("title")){
	    item.title = parser.getText();
	  } else if(tagName.equals("link")){
	    item.link = parser.getText();
	  } else if(tagName.equals("pubDate")){
	    item.pubDate = parser.getText();
	  }
        }
      }
    }
    type = parser.next();
  }
} catch(XmlPullParserException e){
  e.printStackTrace();
} catch(IOException e){
  e.printStackTrace();
}

}


