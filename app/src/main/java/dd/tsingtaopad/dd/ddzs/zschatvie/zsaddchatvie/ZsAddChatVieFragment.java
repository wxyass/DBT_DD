package dd.tsingtaopad.dd.ddzs.zschatvie.zsaddchatvie;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatTextView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import java.util.ArrayList;
import java.util.List;

import dd.tsingtaopad.R;
import dd.tsingtaopad.adapter.ListViewKeyValueAdapter;
import dd.tsingtaopad.base.BaseFragmentSupport;
import dd.tsingtaopad.core.util.dbtutil.CheckUtil;
import dd.tsingtaopad.core.util.dbtutil.FunUtil;
import dd.tsingtaopad.dd.ddxt.chatvie.addchatvie.XtAddChatVieService;
import dd.tsingtaopad.dd.ddxt.checking.domain.XtProItem;
import dd.tsingtaopad.dd.ddxt.invoicing.addinvoicing.XtProKeyValueAdapter;
import dd.tsingtaopad.dd.ddxt.term.select.domain.XtTermSelectMStc;
import dd.tsingtaopad.dd.ddzs.zschatvie.ZsChatvieFragment;
import dd.tsingtaopad.initconstvalues.domain.KvStc;

/**
 * Created by yangwenmin on 2018/3/12.
 * <p>
 * 指标的现有量,变化量 填写界面
 */

public class ZsAddChatVieFragment extends BaseFragmentSupport implements View.OnClickListener {

    private final String TAG = "XtAddChatVieFragment";

    private RelativeLayout backBtn;
    private RelativeLayout confirmBtn;
    private AppCompatTextView confirmTv;
    private AppCompatTextView backTv;
    private AppCompatTextView titleTv;
    private ListView colitemLv;
    private List<XtProItem> tempLst;

    protected String visitId = "b61758cd1d81444da1c8390b0b944a73";// 拜访主表key
    protected String termId = "1-AW46W7";// 终端key
    protected String termName = "坏坏坏";// 终端名称
    protected String seeFlag = "0";// 0:拜访  1:查看
    protected String visitDate;//
    protected String lastTime;//
    protected XtTermSelectMStc termStc;// 终端信息
    protected String channelId = "39DD41A3991E8C68E05010ACE0016FCD";// 终端次渠道(废弃,因为由A->F后,可能在F中修改,但在另一个F中,还是用的A传递过来的,所以废弃)

    private ListView agencyLv;
    private ListView proLv;

    ZsChatvieFragment.MyHandler handler;
    private XtAddChatVieService xtAddChatVieService;

    private List<KvStc> agencyLst;
    private List<KvStc> proLst;

    private ListViewKeyValueAdapter agencyadapte;
    private XtProKeyValueAdapter productAdapter;
    private KvStc agency;// 选择的经销商
    private KvStc product;// 选择的产品
    // 获取用户选择的产品(多选)
    private ArrayList<KvStc> products;

    public ZsAddChatVieFragment() {
    }

    @SuppressLint("ValidFragment")
    public ZsAddChatVieFragment(ZsChatvieFragment.MyHandler handler) {
        this.handler = handler;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_xtbf_addchatvie, container, false);
        initView(view);
        return view;
    }

    // 初始化控件
    private void initView(View view) {
        backBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_back);
        confirmBtn = (RelativeLayout) view.findViewById(R.id.top_navigation_rl_confirm);
        confirmTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_confirm);
        backTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_bt_back);
        titleTv = (AppCompatTextView) view.findViewById(R.id.top_navigation_tv_title);
        confirmBtn.setVisibility(View.VISIBLE);
        confirmBtn.setOnClickListener(this);
        backBtn.setOnClickListener(this);

        agencyLv = (ListView) view.findViewById(R.id.xt_addchatvie_lv_agency);
        proLv = (ListView) view.findViewById(R.id.xt_addchatvie_lv_pro);

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        titleTv.setText("新增竞品关系");
        confirmTv.setText("确定");

        // 获取传递过来的数据
        Bundle bundle = getArguments();
        visitId = bundle.getString("visitKey");
        termId = bundle.getString("termId");
        termName = bundle.getString("termname");
        visitDate = bundle.getString("visitDate");
        lastTime = bundle.getString("lastTime");
        seeFlag = bundle.getString("seeFlag");
        channelId = bundle.getString("channelId");
        termStc = (XtTermSelectMStc) bundle.getSerializable("termStc");

        initData();
    }

    // 初始化数据
    private void initData() {

        xtAddChatVieService = new XtAddChatVieService(getActivity(), null);
        agencyLst = xtAddChatVieService.getAgencyVie();

        if (!CheckUtil.IsEmpty(agencyLst)) {
            agency = agencyLst.get(0);
        }

        agencyadapte = new ListViewKeyValueAdapter(getActivity(),
                agencyLst, new String[]{"key","value"},
                //new int[]{R.drawable.bg_agency_up, R.drawable.bg_agency_down});
                new int[]{R.color.bg_content_color, R.color.bg_content_color_orange});
        agencyLv.setAdapter(agencyadapte);

        products = new ArrayList<KvStc>();
        // 选择不同的经销商,列出不同的产品表
        agencyLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                products = new ArrayList<KvStc>();
                agency = (KvStc)agencyLv.getItemAtPosition(position);
                if (!CheckUtil.IsEmpty(agency.getChildLst())) {
                    product = agency.getChildLst().get(0);
                }
                productAdapter = new XtProKeyValueAdapter(
                        getActivity(),agency.getChildLst(), new String[]{"key","value"},
                        //new int[]{R.drawable.bg_product_up, R.drawable.bg_product_down});
                        new int[]{R.color.bg_content_color, R.color.bg_content_color_orange});
                proLv.setAdapter(productAdapter);
                proLv.refreshDrawableState();

                agencyadapte.setSelectItemId(position);
                agencyadapte.notifyDataSetChanged();
            }
        });

        // 产品
        if (!(agency == null || CheckUtil.IsEmpty(agency.getChildLst()))) {
            product = agency.getChildLst().get(0);
        }
        //productLv.setDividerHeight(0);
        if (!CheckUtil.IsEmpty(agencyLst)) {
            productAdapter = new XtProKeyValueAdapter(
                    getActivity(),agencyLst.get(0).getChildLst(), new String[]{"key","value"},
                    //new int[]{R.drawable.bg_product_up, R.drawable.bg_product_down});
                    new int[]{R.color.bg_content_color, R.color.bg_content_color_orange});
            proLv.setAdapter(productAdapter);
        }

        proLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) {
                product = (KvStc)proLv.getItemAtPosition(position);


                //
                List<String> proIdLst = FunUtil.getPropertyByName(products, "key", String.class);
                if (proIdLst.contains(product.getKey())) {
                    products.remove(product);
                }else{
                    products.add(product);
                }

                //productAdapter.setSelectItemId(position);
                productAdapter.addSelected(position);
                productAdapter.notifyDataSetChanged();
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.top_navigation_rl_back:
                supportFragmentManager.popBackStack();
                break;
            case R.id.top_navigation_rl_confirm:
                //Toast.makeText(getActivity(), "确定", Toast.LENGTH_SHORT).show();
                Message message=new Message();
                Bundle bundle=new Bundle();
                bundle.putSerializable("agency", agency);
                message.setData(bundle);
                message.obj = products;
                message.what=ZsChatvieFragment.ZS_ADD_VIE_SUC;//标志是哪个线程传数据
                handler.sendMessage(message);//发送message信息
                supportFragmentManager.popBackStack();
                break;
            default:
                break;
        }
    }
}
