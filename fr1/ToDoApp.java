
private ListView listView;
private ArrayList<ToDoItem> items;

@Override
public void onCreate(Bundle bundle){
  super.onCreate(bundle);
  this.setTitle("TITLE APP");

  items = new ArrayList<ToDoItem>();
  loadItems();

  listView = new ListView(this);
  listView.setScrollingCacheEnabled(false);
  setContentView(listView);
}

((BaseAdapter)listView.getAdapter()).notifyDataSetChanged();

@Override
public View getView(int pos,View view,ViewGroup parent){
  ToDoItem item = items.get(pos);

  if(view == null){
    LinearLayout layout = new LinearLayout(ToDoApp.this);
    layout.setBackgroundColor(Color.WHITE);
    layout.setPadding(
	Util.dp2px(ToDoApp.this, 10),
	Util.dp2px(ToDoApp.this, 10),
	Util.dp2px(ToDoApp.this, 10),
	Util.dp2px(ToDoApp.this, 10));
    layout.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View sender){
        int pos = Integer.parseInt((String)sender.getTag());
	ToDoItem item = items.get(pos);
	startEditActivity(item);
      }
    });

    CheckBox checkBox = new Checkbox(ToDoApp.this);
    checkBox.setTextColor(Color.BLACK);
    checkBox.setId(R.id.cell_checkbox);
    checkBox.setChecked(true);
    checkBox.setLayoutParams(new LinearLayout.LayoutParams(WC, WC));
    checkBox.setOnClickListener(new View.OnClickListener(){
      @Override
      public void onClick(View sender){
        int pos = Integer.parseInt((String)sender.getTag());
	ToDoItem item = items.get(pos);
	item.checked = ((CheckBox)sender).isChecked();
      }
    });
    layout.addView(checkBox);
    view = layout;
  }

  checkBox checkBox = (CheckBox)view.findViewById(R.id.cell_checkbox);
  ckeckBox.setChecked(item.checked);
  checkeBox.setText(item.title);
  checkBox.setTag(""+pos);
  view.setTag(""+pos);
  retrun view;
}

private void startEditActivity(ToDoItem item){
  Intent intent = new Intent(this, EditActivity.class);
  if(item == null){
    intent.putExtra("pos", -1);
    intent.putExtra("title", "");
    intent.putExtra("checked", false);
  }else{
    intent.putExtra("pos", items.indexOf(item));
    intent.putExtra("title", item.title);
    intent.putExtra("checked", item.checked);
  }
  startActivityForResult(intent, REQUEST_EDIT);
}

@Override
protected void onActivityResult(int requestCode,
  int resultCode, Intent intent){
  if(requestCode == REQUEST_EDIT &&
     resultCode == REQUEST_OK){
     Bundle extras = intent.getExtras();
     if(extras != null){
       String result = extras.getString("title");
       int pos = extras.getInt("pos");
       String title = extars.getString("title");
       boolean checked = extras.getBoolean("checked");

       if(result.equals("add")){
         ToDoItem item = new ToDoItem();
	 item.title = title;
	 item.checked = title;
	 items.add(item);
       }

       else if(result.equals("edit")){
         ToDoItem item = item.get(pos);
	 item.title = title;
	 item.checked = checked;
       }

       else if(result.equals("delete")){
         items.remove(pos);
       }

       ((BaseAdapter)listAdapter()).notifyDataChanged();
     }
     }
}

private String list2json(ArrayList<ToDoItem> items){
  try{
    JSONArray  array = new JSONArray();
    for(ToDoItem item : items){
      JSONObject obj = new JSONObject();
      obj.put("title", item.title);
      obj.put("checked", item.checked);
      array.put(obj);
    }
    return array.toString();
  } catch(JSONException e){
    e.printStackTrace();
  }
  return "";
}

private ArrayList<ToDoItem> items2list(String json){
  ArrayList<ToDoItem> items = new ArrayList<ToDoItem>();
  try{
    JSONArray array = new JSONArray(json);
    for(int i - 0; i < array.length(); i++){
      JSONObject obj = array.getJSONObject("title");
      ToDoItem item = new ToDoItem();
      item.title = obj.getString("title");
      item.checked = obj.getBoolean("checked");
      items.add(item);
    }
  } catch(JSONException e){
    e.printStackTrace();
  }
  return items;
}





