import React, {useState} from "react";
import { ListItem, ListItemText, InputBase, Checkbox, ListItemSecondaryAction, IconButton } from "@mui/material";
import { DeleteOutlined } from "@mui/icons-material";
const Todo = (props) => {
    const [item, setItem] = useState(props.item);
    const [readOnly, setReadOnly] = useState(false);
    const deleteItem = props.deleteItem;
    const editItem = props.editItem;

    const onButtonClick = () => {
        deleteItem(item);
    };

    const turnOffReadOnly = (e) => {
        setReadOnly(false);
    }

    const turnOnReadOnly = (e) => {
        if (e.key === "Enter"){
            e.target.blur();
            setReadOnly(true);
            console.log(e.target.readOnly);
        }
    }

    const editEventHandler = (e) => {
        item.title = e.target.value;
        editItem();
    }

    return (
        <ListItem>
           <Checkbox onClick={()=> {setItem({id : item.id, title : item.title, done : !item.done})}} checked={item.done}/>
           <ListItemText>
                <InputBase 
                    inputProps={{"aria-label" : "naked", "aria-readonly": readOnly}}
                    type="text"
                    id={item.id} 
                    name={item.id} 
                    value={item.title}
                    multiline = {true}
                    fullWidth = {true}
                    onClick={turnOffReadOnly}
                    onKeyDownCapture={turnOnReadOnly}
                    onChange={editEventHandler}
                />
           </ListItemText>
           <ListItemSecondaryAction>
                <IconButton aria-label="Delete Todo" onClick={onButtonClick}>
                    <DeleteOutlined />
                </IconButton>
           </ListItemSecondaryAction>
        </ListItem>
    );
}

export default Todo;