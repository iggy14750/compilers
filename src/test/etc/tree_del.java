public boolean Delete(int v_key) {
    Tree current_node ;
    Tree parent_node ;
    boolean cont ;
    boolean found ;
    boolean ntb ;
    boolean is_root ;
    int key_aux ;

    current_node = this ;
    parent_node = this ;
    cont = true ;
    found = false ;
    is_root = true ;
    while (cont){
        key_aux = current_node.GetKey();
        if (v_key < key_aux)
        	if (current_node.GetHas_Left()){
            	parent_node = current_node ;
            	current_node = current_node.GetLeft() ;
        	}
        	else 
				cont = false ;
        else 
        	if (key_aux < v_key)
            	if (current_node.GetHas_Right()){
            	parent_node = current_node ;
            	current_node = current_node.GetRight() ;
            	}
            	else 
					cont = false ;
        	else { 
            	if (is_root) 
            		if (!current_node.GetHas_Right() && 
                		!current_node.GetHas_Left() )
                		ntb = true ;
            		else 
                		ntb = this.Remove(parent_node,current_node); 
				else 
					ntb = this.Remove(parent_node,current_node);
            		found = true ;
            	cont = false ;
        	}
        	is_root = false ;
    	}
	return found ;
}