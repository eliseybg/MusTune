package com.breaktime.gp_core.core.graphics.control.painters;

import com.breaktime.gp_core.core.graphics.command.*;
import com.breaktime.gp_core.core.ui.resource.UIPainter;

public class TGSilencePainter {
	
	private static final TGPaintCommand WHOLE_MODEL = new TGPaintModel(
		new TGMoveTo(0.04681015f, 0.046812057f),
		new TGLineTo(0.1170311f, 0.0f),
		new TGLineTo(4.3769493f, 0.0f),
		new TGLineTo(8.6602745f, 0.0f),
		new TGLineTo(8.730488f, 0.046812057f),
		new TGLineTo(8.777302f, 0.11703062f),
		new TGLineTo(8.777302f, 1.8256791f),
		new TGLineTo(8.777302f, 3.5343275f),
		new TGLineTo(8.730488f, 3.6045456f),
		new TGLineTo(8.6602745f, 3.6513581f),
		new TGLineTo(4.3769493f, 3.6513581f),
		new TGLineTo(0.1170311f, 3.6513581f),
		new TGLineTo(0.04681015f, 3.6045456f),
		new TGLineTo(0.0f, 3.5343275f),
		new TGLineTo(0.0f, 1.8256791f),
		new TGLineTo(0.0f, 0.11703062f),
		new TGLineTo(0.04681015f, 0.046812057f)
	);
	
	private static final TGPaintCommand HALF_MODEL = new TGPaintModel(
		new TGMoveTo(0.04681015f, 0.046812057f),
		new TGLineTo(0.1170311f, 0.0f),
		new TGLineTo(4.3769493f, 0.0f),
		new TGLineTo(8.6602745f, 0.0f),
		new TGLineTo(8.730488f, 0.046812057f),
		new TGLineTo(8.777302f, 0.11703062f),
		new TGLineTo(8.777302f, 1.8256791f),
		new TGLineTo(8.777302f, 3.5343275f),
		new TGLineTo(8.730488f, 3.6045456f),
		new TGLineTo(8.6602745f, 3.6513581f),
		new TGLineTo(4.3769493f, 3.6513581f),
		new TGLineTo(0.1170311f, 3.6513581f),
		new TGLineTo(0.04681015f, 3.6045456f),
		new TGLineTo(0.0f, 3.5343275f),
		new TGLineTo(0.0f, 1.8256791f),
		new TGLineTo(0.0f, 0.11703062f),
		new TGLineTo(0.04681015f, 0.046812057f)
	);
	
	private static final TGPaintCommand QUARTER_MODEL = new TGPaintModel(
		new TGMoveTo(2.1034088f, 0.047267675f),
		new TGCubicTo(2.1979485f, 0.0f, 2.2924728f, 0.0f, 2.387024f, 0.023633957f),
		new TGCubicTo(2.4579277f, 0.070901394f, 6.121151f, 4.4195156f, 6.2156906f, 4.6085863f),
		new TGCubicTo(6.38113f, 4.915825f, 6.333866f, 5.2230635f, 6.0975113f, 5.601205f),
		new TGCubicTo(5.9084587f, 5.8611765f, 5.5303154f, 6.239316f, 4.821308f, 6.830161f),
		new TGCubicTo(4.4667816f, 7.1137667f, 4.1359215f, 7.4210052f, 4.065014f, 7.491905f),
		new TGCubicTo(3.3323631f, 8.271823f, 3.166935f, 9.453509f, 3.5923424f, 10.351589f),
		new TGCubicTo(3.73415f, 10.65883f, 3.8050346f, 10.729736f, 4.6322327f, 11.675077f),
		new TGCubicTo(6.1447945f, 13.447618f, 6.0738907f, 13.376713f, 6.0738907f, 13.518524f),
		new TGCubicTo(6.0738907f, 13.66031f, 5.9320793f, 13.802121f, 5.790291f, 13.825765f),
		new TGCubicTo(5.6721115f, 13.825765f, 5.5775757f, 13.778487f, 5.4121437f, 13.6130495f),
		new TGCubicTo(4.821308f, 13.02221f, 3.4741745f, 12.5968f, 2.7415237f, 12.762231f),
		new TGCubicTo(2.387024f, 12.833136f, 2.1979485f, 12.974947f, 2.0325203f, 13.305807f),
		new TGCubicTo(1.9379692f, 13.542143f, 1.8670654f, 13.825765f, 1.8434448f, 14.203887f),
		new TGCubicTo(1.8198013f, 14.7238455f, 1.9143524f, 15.267421f, 2.0797806f, 15.787354f),
		new TGCubicTo(2.1979485f, 16.118237f, 2.2688522f, 16.307312f, 2.4342842f, 16.543648f),
		new TGCubicTo(2.5760956f, 16.73272f, 2.5760956f, 16.803608f, 2.4579277f, 16.874508f),
		new TGCubicTo(2.3633804f, 16.921787f, 2.3161163f, 16.898151f, 2.1034088f, 16.638172f),
		new TGCubicTo(1.2526054f, 15.551019f, 0.47267532f, 13.991195f, 0.21271515f, 12.833136f),
		new TGCubicTo(0.0f, 12.005961f, 0.09454727f, 11.438742f, 0.4254074f, 11.202407f),
		new TGCubicTo(0.590847f, 11.107882f, 0.75629425f, 11.084239f, 1.1107941f, 11.084239f),
		new TGCubicTo(1.7961845f, 11.131502f, 2.7651672f, 11.3914795f, 3.7105103f, 11.793245f),
		new TGLineTo(4.01775f, 11.935056f),
		new TGLineTo(2.7178802f, 10.398851f),
		new TGCubicTo(0.73265076f, 8.059115f, 0.66174316f, 7.9645834f, 0.590847f, 7.8227787f),
		new TGCubicTo(0.5199585f, 7.63371f, 0.496315f, 7.4446383f, 0.5672188f, 7.2319345f),
		new TGCubicTo(0.68538666f, 6.853795f, 1.0162506f, 6.4756536f, 2.0088768f, 5.648472f),
		new TGCubicTo(2.3633804f, 5.3648667f, 2.67062f, 5.0812616f, 2.7415237f, 5.033994f),
		new TGCubicTo(3.2851028f, 4.443149f, 3.5450783f, 3.6159673f, 3.450531f, 2.8360534f),
		new TGCubicTo(3.4032707f, 2.5760818f, 3.2851028f, 2.1743064f, 3.1432915f, 1.9616027f),
		new TGCubicTo(3.0723915f, 1.8434343f, 2.7887878f, 1.4889283f, 2.505188f, 1.1344206f),
		new TGCubicTo(2.2215881f, 0.8035486f, 1.9616127f, 0.49630952f, 1.9379692f, 0.44904137f),
		new TGCubicTo(1.8670654f, 0.30723906f, 1.9379692f, 0.11816859f, 2.1034088f, 0.047267675f)
	);
	
	private static final TGPaintCommand EIGHTH_MODEL = new TGPaintModel(
		new TGMoveTo(1.6779978f, 0.070901394f),
		new TGCubicTo(2.1979408f, 0.0f, 2.6469831f, 0.14180231f, 3.0014887f, 0.47267532f),
		new TGCubicTo(3.214193f, 0.7090125f, 3.3087273f, 0.89808273f, 3.450531f, 1.4652932f),
		new TGCubicTo(3.5214317f, 1.7252648f, 3.5923345f, 1.9852359f, 3.6159685f, 2.0325048f),
		new TGCubicTo(3.7105024f, 2.2452075f, 3.8759398f, 2.3870108f, 4.0886426f, 2.4106443f),
		new TGCubicTo(4.183178f, 2.4106443f, 4.2304454f, 2.4106443f, 4.348614f, 2.3397439f),
		new TGCubicTo(4.6558537f, 2.19794f, 5.388501f, 1.3234913f, 5.6721063f, 0.7799146f),
		new TGCubicTo(5.7430067f, 0.6144779f, 5.861175f, 0.5435767f, 5.979345f, 0.5435767f),
		new TGCubicTo(6.073879f, 0.5435767f, 6.192049f, 0.6144779f, 6.239316f, 0.6853788f),
		new TGCubicTo(6.26295f, 0.7326472f, 6.026612f, 1.5598292f, 4.7740216f, 5.7430067f),
		new TGCubicTo(3.970474f, 8.484522f, 3.2850938f, 10.776992f, 3.2614603f, 10.800634f),
		new TGCubicTo(3.2614603f, 10.824278f, 3.1669261f, 10.895159f, 3.0723903f, 10.918801f),
		new TGCubicTo(2.9305882f, 10.989707f, 2.9069548f, 10.989707f, 2.7178838f, 10.989707f),
		new TGCubicTo(2.528813f, 10.989707f, 2.481546f, 10.989707f, 2.3397446f, 10.942445f),
		new TGCubicTo(2.2688415f, 10.895159f, 2.1743073f, 10.847896f, 2.1743073f, 10.824278f),
		new TGCubicTo(2.1270401f, 10.800634f, 2.292475f, 10.375227f, 3.4977982f, 6.94833f),
		new TGCubicTo(4.254079f, 4.844924f, 4.8685584f, 3.0960243f, 4.8449225f, 3.0960243f),
		new TGLineTo(4.4431496f, 3.2141926f),
		new TGCubicTo(3.5923345f, 3.4977977f, 3.0723903f, 3.5923336f, 2.4342787f, 3.5923336f),
		new TGCubicTo(1.914336f, 3.5923336f, 1.7725322f, 3.5687f, 1.394393f, 3.3796299f),
		new TGCubicTo(0.3545066f, 2.88332f, 0.0f, 1.6779974f, 0.5908443f, 0.7799146f),
		new TGCubicTo(0.85081583f, 0.4254074f, 1.2525895f, 0.14180231f, 1.6779978f, 0.070901394f)
	);
	
	private static final TGPaintCommand SIXTEENTH_MODEL = new TGPaintModel(
		new TGMoveTo(3.5214243f, 0.070901394f),
		new TGCubicTo(4.041381f, 0.0f, 4.490409f, 0.14180231f, 4.8449125f, 0.4726758f),
		new TGCubicTo(5.0576286f, 0.7090137f, 5.152153f, 0.89808273f, 5.2939644f, 1.4652946f),
		new TGCubicTo(5.36487f, 1.725266f, 5.435775f, 1.9852376f, 5.4593954f, 2.032505f),
		new TGCubicTo(5.5775614f, 2.3397436f, 5.9084463f, 2.4815469f, 6.144781f, 2.363377f),
		new TGCubicTo(6.4520226f, 2.2215748f, 7.090123f, 1.394392f, 7.421006f, 0.7326474f),
		new TGCubicTo(7.4682703f, 0.6144779f, 7.6100817f, 0.54357696f, 7.704606f, 0.54357696f),
		new TGCubicTo(7.7991533f, 0.54357696f, 7.917321f, 0.6144779f, 7.9645834f, 0.6853788f),
		new TGCubicTo(7.988206f, 0.7326474f, 7.6100817f, 2.150673f, 5.861184f, 8.6972275f),
		new TGCubicTo(4.6794825f, 13.069472f, 3.710497f, 16.685432f, 3.6868773f, 16.709076f),
		new TGCubicTo(3.6632347f, 16.73272f, 3.592329f, 16.803608f, 3.4978046f, 16.827246f),
		new TGCubicTo(3.3559942f, 16.89815f, 3.3323498f, 16.89815f, 3.143302f, 16.89815f),
		new TGCubicTo(2.9542284f, 16.89815f, 2.9069443f, 16.89815f, 2.7651548f, 16.850887f),
		new TGCubicTo(2.69425f, 16.803608f, 2.5997238f, 16.75634f, 2.5997238f, 16.73272f),
		new TGCubicTo(2.5524387f, 16.709076f, 2.69425f, 16.283669f, 3.7341404f, 12.856779f),
		new TGCubicTo(4.372241f, 10.753368f, 4.8921986f, 9.028101f, 4.8921986f, 9.004459f),
		new TGCubicTo(4.868556f, 9.004459f, 4.6794825f, 9.051743f, 4.46679f, 9.122626f),
		new TGCubicTo(3.6159725f, 9.406247f, 3.0723963f, 9.500772f, 2.43427f, 9.500772f),
		new TGCubicTo(1.9143381f, 9.500772f, 1.7725258f, 9.477153f, 1.3943825f, 9.28808f),
		new TGCubicTo(0.3545103f, 8.791764f, 0.0f, 7.586442f, 0.59084797f, 6.6883574f),
		new TGCubicTo(1.1580448f, 5.8611765f, 2.3397465f, 5.7193727f, 3.0014915f, 6.357486f),
		new TGCubicTo(3.2378254f, 6.6174574f, 3.3323498f, 6.830161f, 3.4741611f, 7.4446383f),
		new TGCubicTo(3.5450668f, 7.799144f, 3.6395912f, 8.059116f, 3.7341404f, 8.153648f),
		new TGCubicTo(3.805046f, 8.224551f, 3.9468327f, 8.295452f, 4.088643f, 8.31909f),
		new TGCubicTo(4.1831684f, 8.31909f, 4.2304544f, 8.31909f, 4.3486233f, 8.248187f),
		new TGCubicTo(4.608577f, 8.130019f, 5.152153f, 7.515539f, 5.4593954f, 6.995596f),
		new TGCubicTo(5.5539417f, 6.830161f, 5.601205f, 6.6883574f, 5.766636f, 6.144781f),
		new TGCubicTo(6.2865934f, 4.4904165f, 6.6883574f, 3.119658f, 6.6883574f, 3.0960245f),
		new TGCubicTo(6.6883574f, 3.0960245f, 6.5229273f, 3.1432915f, 6.3574743f, 3.2141924f),
		new TGCubicTo(5.9557085f, 3.3323627f, 5.4121323f, 3.474164f, 5.033985f, 3.5450664f),
		new TGCubicTo(4.7740307f, 3.5923338f, 4.6322203f, 3.5923338f, 4.2777157f, 3.5923338f),
		new TGCubicTo(3.757759f, 3.5923338f, 3.6159725f, 3.5687003f, 3.2378254f, 3.3796296f),
		new TGCubicTo(2.197935f, 2.8833203f, 1.8434324f, 1.6779971f, 2.43427f, 0.77991486f),
		new TGCubicTo(2.69425f, 0.4254074f, 3.096015f, 0.14180231f, 3.5214243f, 0.070901394f)
	);
	
	private static final TGPaintCommand THIRTY_SECOND_MODEL = new TGPaintModel(
		new TGMoveTo(4.939474f, 0.070901155f),
		new TGCubicTo(5.459408f, 0.0f, 5.9084606f, 0.14180207f, 6.2629623f, 0.4726758f),
		new TGCubicTo(6.4756565f, 0.70901346f, 6.570204f, 0.89808273f, 6.7119904f, 1.4652941f),
		new TGCubicTo(6.782896f, 1.7252657f, 6.8538036f, 1.9852374f, 6.877445f, 2.0325048f),
		new TGCubicTo(6.9719696f, 2.245209f, 7.1373997f, 2.3870108f, 7.3501167f, 2.4106443f),
		new TGCubicTo(7.444641f, 2.4106443f, 7.4919033f, 2.4106443f, 7.562809f, 2.3633773f),
		new TGCubicTo(7.8227882f, 2.19794f, 8.34272f, 1.4889283f, 8.697241f, 0.80354834f),
		new TGCubicTo(8.768129f, 0.61447763f, 8.862679f, 0.54357696f, 9.004465f, 0.54357696f),
		new TGCubicTo(9.099014f, 0.54357696f, 9.217182f, 0.61447763f, 9.264444f, 0.6853788f),
		new TGCubicTo(9.288086f, 0.7326474f, 8.815416f, 2.7887857f, 6.735634f, 11.627814f),
		new TGCubicTo(5.3175983f, 17.6308f, 4.1359196f, 22.57025f, 4.1359196f, 22.593893f),
		new TGCubicTo(4.112276f, 22.641155f, 4.041378f, 22.688417f, 3.946846f, 22.73568f),
		new TGCubicTo(3.781416f, 22.806585f, 3.7577744f, 22.806585f, 3.5687008f, 22.806585f),
		new TGCubicTo(3.3796272f, 22.806585f, 3.332365f, 22.806585f, 3.1905785f, 22.759323f),
		new TGCubicTo(3.1196728f, 22.71206f, 3.0251236f, 22.664799f, 3.0251236f, 22.641155f),
		new TGCubicTo(2.9778614f, 22.617512f, 3.0960293f, 22.192102f, 3.9941082f, 18.765215f),
		new TGCubicTo(4.5376854f, 16.661814f, 4.986738f, 14.936537f, 5.0103607f, 14.912916f),
		new TGCubicTo(5.0103607f, 14.889275f, 4.9158325f, 14.889275f, 4.6322346f, 15.007441f),
		new TGCubicTo(3.7105122f, 15.314682f, 2.883337f, 15.456493f, 2.2688541f, 15.43285f),
		new TGCubicTo(1.9379692f, 15.409229f, 1.6780167f, 15.3383255f, 1.3943939f, 15.196516f),
		new TGCubicTo(0.35450363f, 14.700201f, 0.0f, 13.49488f, 0.59086037f, 12.5968f),
		new TGCubicTo(1.1580582f, 11.769626f, 2.3397598f, 11.627814f, 3.001505f, 12.265927f),
		new TGCubicTo(3.2378407f, 12.525894f, 3.332365f, 12.738611f, 3.4741764f, 13.353088f),
		new TGCubicTo(3.5450802f, 13.707598f, 3.6396065f, 13.967552f, 3.7341537f, 14.062099f),
		new TGCubicTo(3.8286781f, 14.133005f, 3.9704895f, 14.203886f, 4.112276f, 14.22753f),
		new TGCubicTo(4.2068253f, 14.22753f, 4.2540874f, 14.22753f, 4.348612f, 14.156624f),
		new TGCubicTo(4.679497f, 13.991194f, 5.3648834f, 13.140376f, 5.5775776f, 12.667706f),
		new TGCubicTo(5.601219f, 12.573157f, 6.546562f, 9.051743f, 6.546562f, 9.004459f),
		new TGCubicTo(6.546562f, 8.980838f, 6.404751f, 9.0281f, 6.2393208f, 9.099006f),
		new TGCubicTo(5.7430058f, 9.240808f, 5.2939777f, 9.382604f, 4.8685703f, 9.453508f),
		new TGCubicTo(4.561329f, 9.5007715f, 4.443161f, 9.5007715f, 4.065014f, 9.5007715f),
		new TGCubicTo(3.5450802f, 9.5007715f, 3.4032707f, 9.477151f, 3.0251236f, 9.288079f),
		new TGCubicTo(1.9852371f, 8.791764f, 1.6307297f, 7.586441f, 2.2215939f, 6.6883574f),
		new TGCubicTo(2.7887878f, 5.8611755f, 3.9704895f, 5.7193727f, 4.6322346f, 6.357485f),
		new TGCubicTo(4.8685703f, 6.6174564f, 4.9630947f, 6.830161f, 5.104904f, 7.4446383f),
		new TGCubicTo(5.175812f, 7.799144f, 5.270336f, 8.059115f, 5.3648834f, 8.153648f),
		new TGCubicTo(5.50667f, 8.295452f, 5.7666492f, 8.366357f, 5.9320793f, 8.271823f),
		new TGCubicTo(6.168415f, 8.177285f, 6.4756565f, 7.8227797f, 6.782896f, 7.3501034f),
		new TGCubicTo(7.113781f, 6.877428f, 7.0901375f, 6.948329f, 7.610079f, 4.963092f),
		new TGCubicTo(7.8700504f, 3.970473f, 8.082766f, 3.1432917f, 8.082766f, 3.1196578f),
		new TGCubicTo(8.082766f, 3.1196578f, 7.9173126f, 3.1432917f, 7.72826f, 3.2141926f),
		new TGCubicTo(7.279211f, 3.355996f, 6.830158f, 3.4741638f, 6.452036f, 3.545066f),
		new TGCubicTo(6.1920567f, 3.592334f, 6.050247f, 3.592334f, 5.6957436f, 3.592334f),
		new TGCubicTo(5.175812f, 3.592334f, 5.0339985f, 3.5687f, 4.6558533f, 3.3796294f),
		new TGCubicTo(3.6159801f, 2.88332f, 3.2614594f, 1.6779974f, 3.8523216f, 0.7799146f),
		new TGCubicTo(4.112276f, 0.4254074f, 4.5140667f, 0.14180207f, 4.939474f, 0.070901155f)
	);
	
	private static final TGPaintCommand SIXTY_FOURTH_MODEL = new TGPaintModel(
		new TGMoveTo(5.908436f, 0.07090092f),
		new TGCubicTo(6.4283676f, 0.0f, 6.8774185f, 0.14180303f, 7.231926f, 0.4726758f),
		new TGCubicTo(7.4446335f, 0.70901346f, 7.5391655f, 0.8980839f, 7.680977f, 1.4652941f),
		new TGCubicTo(7.7991447f, 1.9616034f, 7.846405f, 2.1034052f, 7.9409294f, 2.221575f),
		new TGCubicTo(8.082741f, 2.3870103f, 8.366341f, 2.4579127f, 8.531792f, 2.3633769f),
		new TGCubicTo(8.72084f, 2.2688425f, 9.028084f, 1.8434336f, 9.382591f, 1.1344209f),
		new TGCubicTo(9.642563f, 0.61447763f, 9.689827f, 0.5672102f, 9.855282f, 0.5672102f),
		new TGCubicTo(9.949806f, 0.5672102f, 10.067974f, 0.6381123f, 10.115234f, 0.70901346f),
		new TGCubicTo(10.138878f, 0.75628066f, 9.571659f, 3.592333f, 7.3264503f, 14.605665f),
		new TGCubicTo(5.790268f, 22.215736f, 4.514042f, 28.478699f, 4.514042f, 28.502335f),
		new TGCubicTo(4.4903984f, 28.549604f, 4.4195137f, 28.596869f, 4.3249702f, 28.644129f),
		new TGCubicTo(4.1595383f, 28.715034f, 4.135895f, 28.715034f, 3.9468231f, 28.715034f),
		new TGCubicTo(3.7577515f, 28.715034f, 3.7104874f, 28.715034f, 3.568699f, 28.667772f),
		new TGCubicTo(3.497795f, 28.62051f, 3.403244f, 28.573223f, 3.403244f, 28.549604f),
		new TGCubicTo(3.3559837f, 28.525963f, 3.450531f, 28.100554f, 4.230446f, 24.65002f),
		new TGCubicTo(4.7031136f, 22.522978f, 5.0812607f, 20.797724f, 5.0812607f, 20.77408f),
		new TGCubicTo(5.0812607f, 20.77408f, 4.892189f, 20.821352f, 4.679474f, 20.892248f),
		new TGCubicTo(4.1595383f, 21.081322f, 3.7341309f, 21.175869f, 3.28508f, 21.270393f),
		new TGCubicTo(2.954216f, 21.317657f, 2.836052f, 21.317657f, 2.4579048f, 21.317657f),
		new TGCubicTo(1.9143257f, 21.317657f, 1.7725143f, 21.294037f, 1.3943901f, 21.104965f),
		new TGCubicTo(0.35450363f, 20.60865f, 0.0f, 19.40333f, 0.5908394f, 18.505249f),
		new TGCubicTo(1.1580353f, 17.678051f, 2.339737f, 17.536264f, 3.00148f, 18.174364f),
		new TGCubicTo(3.2378159f, 18.434345f, 3.3323517f, 18.647038f, 3.4741516f, 19.261518f),
		new TGCubicTo(3.5450554f, 19.616022f, 3.6395798f, 19.876f, 3.7341309f, 19.970549f),
		new TGCubicTo(3.8050346f, 20.04143f, 3.9468231f, 20.112335f, 4.0886345f, 20.135979f),
		new TGCubicTo(4.2540627f, 20.1596f, 4.395874f, 20.065073f, 4.6558533f, 19.805096f),
		new TGCubicTo(5.010357f, 19.450592f, 5.459385f, 18.812492f, 5.577553f, 18.505249f),
		new TGCubicTo(5.6011925f, 18.434345f, 5.790268f, 17.60717f, 6.00296f, 16.661804f),
		new TGCubicTo(6.2156754f, 15.716461f, 6.3811073f, 14.93655f, 6.404751f, 14.912907f),
		new TGCubicTo(6.404751f, 14.889288f, 6.333843f, 14.889288f, 6.0266037f, 15.007456f),
		new TGCubicTo(5.1048813f, 15.314692f, 4.277706f, 15.456482f, 3.6632233f, 15.432863f),
		new TGCubicTo(3.3323517f, 15.409222f, 3.0723839f, 15.338314f, 2.7887688f, 15.19651f),
		new TGCubicTo(1.7488976f, 14.700214f, 1.3943901f, 13.494894f, 1.9852333f, 12.59679f),
		new TGCubicTo(2.2688293f, 12.17139f, 2.7415009f, 11.911427f, 3.28508f, 11.864165f),
		new TGCubicTo(3.7104874f, 11.840521f, 4.0886345f, 11.982332f, 4.395874f, 12.289569f),
		new TGCubicTo(4.6085663f, 12.525908f, 4.7031136f, 12.714971f, 4.8449097f, 13.282178f),
		new TGCubicTo(4.915806f, 13.542156f, 4.9867134f, 13.802132f, 5.010357f, 13.849398f),
		new TGCubicTo(5.128525f, 14.156637f, 5.459385f, 14.298426f, 5.7193604f, 14.180258f),
		new TGCubicTo(6.00296f, 14.038469f, 6.6647034f, 13.187653f, 6.8774185f, 12.691338f),
		new TGCubicTo(6.9010506f, 12.620433f, 7.1137543f, 11.745991f, 7.3264503f, 10.777008f),
		new TGLineTo(7.728237f, 8.980829f),
		new TGLineTo(7.3028297f, 9.122639f),
		new TGCubicTo(6.4283676f, 9.406237f, 5.8847923f, 9.500785f, 5.246689f, 9.500785f),
		new TGCubicTo(4.726738f, 9.500785f, 4.5849457f, 9.47714f, 4.2067986f, 9.288069f),
		new TGCubicTo(3.1669083f, 8.791758f, 2.8124046f, 7.586441f, 3.403244f, 6.6883574f),
		new TGCubicTo(3.6868668f, 6.26295f, 4.1595383f, 6.0029783f, 4.7031136f, 5.9557104f),
		new TGCubicTo(5.128525f, 5.9320765f, 5.506672f, 6.07388f, 5.8138924f, 6.381119f),
		new TGCubicTo(6.0266037f, 6.6174564f, 6.121128f, 6.806527f, 6.2629395f, 7.3737373f),
		new TGCubicTo(6.3811073f, 7.8700476f, 6.4283676f, 8.0118475f, 6.5229187f, 8.130014f),
		new TGCubicTo(6.688347f, 8.295459f, 6.948326f, 8.366352f, 7.1137543f, 8.271822f),
		new TGCubicTo(7.397354f, 8.130014f, 7.846405f, 7.539174f, 8.130005f, 6.948328f),
		new TGCubicTo(8.224552f, 6.759259f, 8.271812f, 6.5701895f, 8.62632f, 4.939458f),
		new TGLineTo(9.028084f, 3.1196573f),
		new TGCubicTo(9.028084f, 3.0960238f, 8.886295f, 3.1432912f, 8.72084f, 3.2141936f),
		new TGCubicTo(7.8227615f, 3.4977987f, 7.066494f, 3.6396005f, 6.4992714f, 3.6159666f),
		new TGCubicTo(6.168415f, 3.592333f, 5.908436f, 3.5214326f, 5.624836f, 3.379629f),
		new TGCubicTo(4.5849457f, 2.8833196f, 4.230446f, 1.6779983f, 4.8212814f, 0.7799144f),
		new TGCubicTo(5.0812607f, 0.42540812f, 5.4830284f, 0.14180303f, 5.908436f, 0.07090092f)
	);
	
	public static void paintWhole(UIPainter painter, float x, float y,float scale){
		WHOLE_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintHalf(UIPainter painter, float x, float y,float scale){
		HALF_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintQuarter(UIPainter painter, float x, float y,float scale){
		QUARTER_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintEighth(UIPainter painter, float x, float y,float scale){
		EIGHTH_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintSixteenth(UIPainter painter, float x, float y,float scale){
		SIXTEENTH_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintThirtySecond(UIPainter painter, float x, float y, float scale){
		THIRTY_SECOND_MODEL.paint(painter, x, y, scale);
	}
	
	public static void paintSixtyFourth(UIPainter painter, float x, float y,float scale){
		SIXTY_FOURTH_MODEL.paint(painter, x, y, scale);
	}
}
