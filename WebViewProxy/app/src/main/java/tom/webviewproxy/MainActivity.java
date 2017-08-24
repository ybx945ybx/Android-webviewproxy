package tom.webviewproxy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    private String url = "https://www.baidu.com/s?wd=%E6%9F%A5%E7%9C%8Bip&rsv_spt=1&rsv_iqid=0x80a99a9c0000b4ef&issp=1&f=8&rsv_bp=0&rsv_idx=2&ie=utf-8&tn=baiduhome_pg&rsv_enter=1&rsv_sug3=13&rsv_sug1=12&rsv_sug7=101";
    private String host = "114.112.81.38";
    private int port = 1099;
    private String applictionName = "tom.webviewproxy.BaseApplication";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = (WebView) findViewById(R.id.webview);
        ProxyUtils.setProxy(webView, host, port, applictionName);
        webView.loadUrl(url);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return false;
            }

        });

        findViewById(R.id.tv_ip_set).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "设置了代理", Toast.LENGTH_SHORT).show();
                ProxyUtils.setProxy(webView, host, port, applictionName);
            }
        });


        findViewById(R.id.tv_ip_clear).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "清空了代理", Toast.LENGTH_SHORT).show();
                ProxyUtils.clearProxy(webView);
            }
        });
    }

    // 退出时清空代理
    @Override
    protected void onDestroy() {
        super.onDestroy();
        ProxyUtils.clearProxy(webView);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (webView.canGoBack()) {
            webView.goBack();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
