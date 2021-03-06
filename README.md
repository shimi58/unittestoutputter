# unit-test-outputter

    テストコードをExcelに出力する

## 出力方法

1. Cドライブ直下にTEMPフォルダを作成

~~~path
C:\TEMP
~~~

1. TEMPフォルダ直下に以下のファイルを格納
- UnitTestTemplate.xlsx(templateフォルダのファイルをコピーする)
- unittestoutputter.jar(moduleフォルダのファイルをコピーする)

1. JavaDoc作成を行いたいプロジェクトを選択、「カスタムドックレットを使用」を選択し、以下の内容を入力する

~~~text
ドックレット名：unittestoutputter.UnitTestOutputter
ドックレット・クラスパス：C:\TEMP\unittestoutputter.jar
~~~

  ![unittestoutputter_01](https://github.com/shimi58/unittestoutputter/blob/image/images/unittestoutputter_01.png)

1. 追加のJavadocオプションに以下を入力する

~~~text
-encoding UTF-8
~~~


## JavaDocコメントフォーマット

|  部品  |               内容               |
| ------ | -------------------------------- |
| \<br>  | 改行コード。同じ項目内で改行する |
| \<br/> | 次のセルに移動する               |
| ★\<br> | 項目を分割する                   |


## Excel出力内容

| 順序 |   項目   |            出力内容            |
| ---- | -------- | ------------------------------ |
| 0    | 試験No   | ツール側で自動採番             |
| 1    | 項目     | メソッドタイトル+メソッド名    |
| 2    | 実施方法 | JavaDoc記載内容                |
| 3    | 確認事項 | JavaDoc記載内容                |
| 4    | 日付     | 作成日付                       |
| 5    | 結果     | ツール側で自動設定。OKのみ入力 |

- 記載例

~~~java
    /**
     * 単体項目内容の読み込み確認.<br/>
     * ■実施内容<br>
     * ①1項目出力<br/>
     * ■確認内容<br>
     * ①期待通りとなること<br/>
     * ★<br>
     * ■実施内容<br>
     * ②1項目出力<br>
     * 改行あり<br/>
     * ■確認内容<br>
     * ②期待通りとなること<br/>
     */
~~~

  ![unittestoutputter_02](https://github.com/shimi58/unittestoutputter/blob/image/images/unittestoutputter_02.png)
  ![unittestoutputter_03](https://github.com/shimi58/unittestoutputter/blob/image/images/unittestoutputter_03.png)
