
@Override
public void onCreate(Bundle bundle){ 
  super.onCreate(bundle);
  this.setTitle(pos<0?"ADD":"EDIT");

  pos = -1;
  toDoItem = new ToDoItem();
  Bundle extras = getIntent().getExtras();
  if(extras != null){
    pos = extras.getInt("pos");
    toDoItem.title = extras.getString("title");
    toDoItem.checked = extras.getBoolean("checked");
  }
}

@Override
public boolean onOptionsItemSelected(MenuItem item){
  int itemId = item.getItemId();
  if(itemId == MENU_ITEM0 || itemId == MENU_ITEM1){
    String result = null;
    if(itemId == MENU_ITEM0) result = "delete";
    if(itemId == MENU_ITEM1) result = pos;

    Intent intent = new Intent();
    intent.putExtra("result", result);
    intent.putExtra("pos", pos);
    intent.putExtra("title", toDoItem.title);
    intent.putExtra("checked", toDoItem.checked);
    setResult(Activity.RESULT_OK, intent);
    finish();
  }
  return ture;
}



