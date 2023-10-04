import { useState } from 'react';
import './App.css';
import Todo from './Todo';
import { Paper, List, Container, InputBase, ListItemText } from '@mui/material';
import AddTodo from './AddTodo';

function App() {
  const [items, setItems] = useState([]);

  const addItem = (item) => {
    if (!item.title){
      alert("제목을 입력해주세요");
      return;
    }
    item.id = "ID-" + items.length;
    item.done = false;
    setItems([...items, item]);
    console.log(items);
  }

  const deleteItem = (item) => {
    let newItems = items.filter((e) => e.id !== item.id)
    setItems([...newItems]);
  }

  const editItem = () => {
    setItems([...items]);
    console.log(items);
  }

  let todoItems = items.length > 0 && (
    <Paper style={{margin : 16}}>
      <List>
        {items.map((item) => <Todo item={item} key={item.id} deleteItem = {deleteItem} editItem={editItem}/>)}
      </List>
    </Paper>
  );

  return (
    <div className="App">
      <Container maxWidth="md">
        <AddTodo addItem={addItem}/>
        <div className='TodoList'>{todoItems}</div>
        <ListItemText>
          <InputBase type='text' readOnly={false} value="test1"></InputBase>
          <InputBase type='text' readOnly={true} value="test2"></InputBase>
        </ListItemText>
      </Container>
    </div>
  );
}

export default App;
