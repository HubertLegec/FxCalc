<h1>Zadanie 1 - Kalkulator w technologii JavaFX</h1>

<h2>Kalkulator</h2>

<p>
Napisać kalkulator w technologii JavaFx z wykorzystaniem SceneBuildera.
Kalkulator wykonuje podstawowe działania, liczy pierwiastek oraz procent.
Przy błędzie obliczeniowym na wyświetlaczu należy wyświetlić napis ERR, zablokować dostęp do wszystkich klawiszy (wyszarzając je) poza klawiszem C.
Aplikacja powinna umożliwiać rozciąganie - mieć ustalone rozmiar minimalny i maksymalny, proszę aby różnica rozmiarów była znaczna (>3x)
</p>

<h2>Opis zadania</h2>

<ul>
<li>Tworzymy projekt typu JavaFx Project. Nazywamy go FxCalcNazwisko</li>
<li>
<p>Tworzymy kontroler, zgodnie ze wzorem:</p>
<pre>
<code>
public class FxCalc extends Application {
  @Override
  public void start(Stage primaryStage) {
    try {
      Parent root = FXMLLoader.load(getClass()
                              .getResource("fxcalc.fxml"));
      Scene scene = new Scene(root,400,400);
      scene.getStylesheets().add(getClass()
           .getResource("fxcalc.css").toExternalForm());
      primaryStage.setScene(scene);
      primaryStage.setTitle("Scene Buildered");
      primaryStage.show();
    } catch(Exception e) {
      e.printStackTrace();
    }
  }
  @FXML
  public void Click(ActionEvent event) {
    System.out.println(((Button)event.getSource()).getText());
  }
  @FXML
  private TextField display;
  public static void main(String[] args) {
    launch(args);
  }
}
</code>
</pre>
</li>
<li>	
<p>zmieniamy nazwę pliku application.css na fxscene.css. Definiujemy właściwości przycisku (przykład, proszę o rozwinięcie)</p>
<pre>
<code>
.root {
    -fx-font-size: 26px;
}
.button {
    -fx-background-radius: 5%;
    -fx-font-size: 20px;
    -fx-max-width:2000px;
    -fx-max-height:2000px;
}
</code>
</pre>
</li>
		
<li>Tworzymy nowy plik typu New FXML Document w pakiecie application. Wybieramy GridPane jako typ węzła. Plik nazywamy fxcalc.</li>
<li>Otwieramy plik fxcalc.fxml wybierając Open with SceneBuilder z menu kontekstowego.</li>
<li>Z menu Preview->Scene Style Sheets wybieramy Open Style Sheet i wskazujemy na dopiero co utworzony fxscene.css</li>
<li>Ustawiamy przyciski na gridzie. Dla przycisku '=' ustawiamy przycisk normalny w górnym wierszu i zmieniamy jego właściwość Row Span na 2 (zakładka layout)</li>
<li>Za pomocą żółtych rączek wybieramy ograniczenia dla wierszy i kolumn, ustawiamy odpowiednio HGROW i VGROW (zakładka Layout) na ALWAYS (kolumny i wiersze będą chciały zająć całą dostępną przestrzeń.</li>
<li>W zakładce 'Controller' w lewym dolnym rogu wybieramy plik java z kontrolerem i klasę kontrolera.</li>
<li>Dla każdego klawisza w zakładce Code wybieramy z listy zdarzenie Click (można mieć kilka grup zdarzeń). Jeżeli zdarzenie podpięte do kilku przycisków możemy rozponać ktory został wciśnięty na podstawie parametru event</li>
<li>Dla pola tekstowego ustawiamy COL-SPAN na 4, MAX-WIDTH, MAX-HEIGHT na ALWAYS, ... oraz w zakładce code ustawiamy fx:id na wybraną z listy opcję display (czyli podłączamy się do pola kontrolera)</li>
<li>Wykorzystując właściwości i interfejs funkcjonalny spróbować osiągnąć funkcjonalność skalowania się fontu wraz ze zwiększaniem się przycisku</li>
</ul>