Unified parser can be used to parse key-value record which with various format from binary array to a custom record object -- IKeyValueRecord

IKeyValueRecord is a interface present a key-value record with method put(k,v) and get(k),  it has a container that can keep all the key-value pair.
Inside the project,  MapBaseRecord is a implement for IKeyValueRecord.

IKeyValueRecordParser is a interface witch can parse a key-value record from binary array to IKeyValueRecord.
IKeyValueReader can help you to read key and value iteratively.It can be used in two ways:
    One is to help you parse a binary key-value record. And the other is to help you the read all the key-values in a IKeyValueRecord.interface