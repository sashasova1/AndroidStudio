<?php

class DB {
    private $link;
    private $err;
    public function connect() {
        $this->link = new \mysqli (
            \Config::$server, \Config::$user, \Config::$pwd, \Config::$db
        );
        if (!$this->link) {
            return false;
        }
        $this->runQuery("SQL SET NAMES 'utf-8");
        return true;
    }
    public function disconnect() {
        $this->link->close();
        unset($this->link);
    }
    public function runQuery($sql) {
        if(!$this->link) {
            $this->connect();
        }
        $res = $this->link->query($sql);
        if (!$res) {
            $this->err = $this->link->error;
        }
        return $res;
    }
    public function getArrFromQuery($sql) {
        $res_arr = [];
        $rs = $this->runQuery($sql);
        while ($row = $rs->fetch_assoc()) {
            $res_arr[] = $row;
        }
        return $res_arr;
    }
}

?>