document.addEventListener("DOMContentLoaded", () => {
  const citySelect = document.getElementById("city-select");
  const getWeatherButton = document.getElementById("get-weather");


  if (getWeatherButton) {
    getWeatherButton.addEventListener("click", fetchWeather);
  } else {
    console.error("ID 'get-weather' のボタンが見つかりません。");
  }

  function fetchWeather() {
    const cityCode = citySelect.value;

    if (!cityCode) {
      alert("地域を選択してください。"); 
      return;
    }

    if (getWeatherButton) {
      getWeatherButton.disabled = true;
    }

    const apiUrl = `https://www.jma.go.jp/bosai/forecast/data/forecast/${cityCode}.json`;

    fetch(apiUrl)
      .then(response => {
        if (!response.ok) {
          throw new Error(`HTTP error! status: ${response.status}`);
        }
        return response.json(); 
      })
      .then(weather => {
                const forecastOverview = weather[0];
        if (!forecastOverview) throw new Error("予報データ(weather[0])がありません。");

        const weatherDataBlock = forecastOverview.timeSeries.find(ts => 
          ts.areas && ts.areas[0] && ts.areas[0].weathers
        );
        
        if (!weatherDataBlock) throw new Error("天気データ(weathers)が見つかりません。");

        const publishingOffice = forecastOverview.publishingOffice || 'N/A';
        const reportDatetime = forecastOverview.reportDatetime || 'N/A';
        
        const targetArea = weatherDataBlock.areas[0].area.name || 'N/A';
        const todayWeather = weatherDataBlock.areas[0].weathers[0] || 'N/A';
        const tomorrowWeather = weatherDataBlock.areas[0].weathers[1] || 'N/A';
        const dayAfterTomorrowWeather = weatherDataBlock.areas[0].weathers[2] || 'N/A';

        let lowTemp = 'N/A';
        let highTemp = 'N/A';
        
        const weeklyOverview = weather[1];
        
        if (weeklyOverview && weeklyOverview.tempAverage && weeklyOverview.tempAverage.areas) {
          
          const avgData = weeklyOverview.tempAverage.areas[0];

          if (avgData) {
            if (avgData.max && avgData.max !== "-") {
              highTemp = `${avgData.max}℃`;
            }
            if (avgData.min && avgData.min !== "-") {
              lowTemp = `${avgData.min}℃`;
            }
          } else {
             console.warn(`地域コード ${cityCode} の平年値データ(tempAverage.areas[0])が見つかりません。`);
          }
        } else {
          console.warn(`地域コード ${cityCode} の平年値データ(tempAverage)が見つかりません。`);
        }
        
        setCellText("publishingOffice", publishingOffice);
        setCellText("reportDatetime", reportDatetime);
        setCellText("targetArea", targetArea);
        
        setCellText("todayHighTemperature", highTemp);
        setCellText("todayLowTemperature", lowTemp);
        
        setCellText("today", todayWeather);
        setCellText("tomorrow", tomorrowWeather);
        setCellText("dayAfterTomorrow", dayAfterTomorrowWeather);
      })
      .catch(error => {
        console.error("天気データの取得に失敗しました:", error);
        alert("天気データの取得に失敗しました。コンソールを確認してください。");
      })
      .finally(() => {
        if (getWeatherButton) {
          getWeatherButton.disabled = false;
        }
      });
  }

  function setCellText(rowId, text) {
    const row = document.getElementById(rowId);
    if (row && row.lastElementChild) {
      row.lastElementChild.textContent = text;
    } else {
      console.warn(`ID '${rowId}' の行、またはその子要素(<td>)が見つかりません。`);
    }
  }
});