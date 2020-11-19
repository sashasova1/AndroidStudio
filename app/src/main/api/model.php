<?php

class Model {
    public static function getAuthorsList() {
        return (new DB())->getArrFromQuery(
            "SELECT id, name, birthplace, litDirection, uaLangFlg, rusLangFlg
          FROM authors
          order by name");
    }
    public static function getAuthor($id){
        return (new DB())->getArrFromQuery(
            "SELECT id, name, birthplace, litDirection, uaLangFlg, rusLangFlg
          FROM authors where id=$id");
    }
}

?>