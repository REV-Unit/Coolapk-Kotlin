package org.revunit.coolapkkt.network.data.response

import com.google.gson.annotations.SerializedName

data class PicRecommendData(var data: List<DataBean>? = null) {
    /**
     * device_title : 小米8
     * dyh_name :
     * extra_info :
     * extra_key : 4d510761716764743052f88443d10a8e
     * extra_pic :
     * extra_title :
     * extra_url :
     * forwardid :
     * fromname : 酷安客户端
     * istag : 1
     * label : 1080x1350
     * lastupdate : 1585727173
     * likenum : 27
     * location :
     * media_pic :
     * media_type : 0
     * media_url :
     * message : [#艺术#](/t/艺术?type=8) 花花花花花里胡哨
     * 出自ins：dorianlegret
     * 地址[查看链接](https://www.instagram.com/p/B7Y6m8uoRre/?igshid=ne0c5h4l9byb)
     * message_cover :
     * message_keywords :
     * message_length : 95
     * message_status : -13
     * message_title :
     * pic : http://image.coolapk.com/picture/2020/0401/15/427022_7161_2442@1080x1350.jpg
     * question_answer_num : 0
     * question_follow_num : 0
     * rank_score : 0
     * recent_hot_reply_ids :
     * recent_reply_ids :
     * recommend : 1
     * related_dyh_ids : 3036
     * relatednum : 0
     * replynum : 0
     * reportnum : 0
     * source_id :
     * status : 1
     * tags : #艺术#
     * tid : 0
     * tinfo :
     * tpic :
     * ttitle :
     * turl :
     * type : 8
     * uid : 427022
     * user_tags :
     * username : 不要看钟
     * favnum : 14
     * issummary : 0
     * is_html_article : 0
     * is_hidden : 0
     * is_anonymous : 0
     * id : 17750832
     * fromid : 2
     * forwardnum : 0
     * fid : 0
     * extra_type : 0
     * extra_status : 0
     * dyh_id : 0
     * dateline : 1585727173
     * comment_block_num : 0
     * block_status : 0
     * index_name : feed
     * _queryTotal : 16271
     * _queryViewTotal : 5000
     * _querySearchTime : 0.016255
     * fetchType : base
     * entityId : 17750832
     * avatarFetchType : batch
     * userAvatar : http://avatar.coolapk.com/data/000/42/70/22_avatar_middle.jpg?1585702779
     * entityTemplate : feed
     * entityType : feed
     * url : /picture/17750832
     * feedType : picture
     * feedTypeName : 酷图
     * turlTarget :
     * info :
     * infoHtml :
     * picArr : ["http://image.coolapk.com/picture/2020/0401/15/427022_7161_2442@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7163_0089@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7164_4409@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7165_7444@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7167_0025@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7168_3494@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7169_6728@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7170_9083@1080x1350.jpg","http://image.coolapk.com/picture/2020/0401/15/427022_7172_1867@1080x1350.jpg"]
     * relateddata : []
     * media_info :
     * sourceFeed : null
     * forwardSourceType :
     * shareUrl : https://www.coolapk.com/picture/17750832?shareKey=NjEyYzgyOGIzZjM4NWU4NDYzNzM~
     * title : 不要看钟的酷图
     * extra_fromApi : /feed/coolPictureList?fragmentTemplate=flex
     * userInfo : {"uid":427022,"username":"不要看钟","admintype":0,"groupid":0,"usergroupid":111,"level":11,"status":1,"usernamestatus":1,"avatarstatus":1585702779,"avatar_cover_status":1585671922,"regdate":1416118485,"logintime":1585733076,"fetchType":"base","entityType":"user","entityId":427022,"displayUsername":"不要看钟","url":"/u/427022","userAvatar":"http://avatar.coolapk.com/data/000/42/70/22_avatar_middle.jpg?1585702779","userSmallAvatar":"http://avatar.coolapk.com/data/000/42/70/22_avatar_small.jpg?1585702779","userBigAvatar":"http://avatar.coolapk.com/data/000/42/70/22_avatar_big.jpg?1585702779","cover":"http://avatar.coolapk.com/cover/15/85/67/427022_1585671922.jpg","verify_status":0,"verify_icon":"","verify_label":"","verify_title":""}
     * relationRows : [{"id":3036,"logo":"http://image.coolapk.com/dyh_logo/2019/0218/12/ab1cbaa511891eb84d340532cd55864e-dyh-uid-691103@694x694.jpg","title":"是一见钟情","url":"/dyh/3036","entityType":"feedRelation"}]
     * _tid : 0
     * replyRows : []
     * replyRowsCount : 0
     * replyRowsMore : 0
     */
    data class DataBean(
        var device_title: String? = null,
        var dyh_name: String? = null,
        var extra_info: String? = null,
        var extra_key: String? = null,
        var extra_pic: String? = null,
        var extra_title: String? = null,
        var extra_url: String? = null,
        var forwardid: String? = null,
        var fromname: String? = null,
        var istag: String? = null,
        var label: String? = null,
        var lastupdate: String? = null,
        var likenum: String? = null,
        var location: String? = null,
        var media_pic: String? = null,
        var media_type: String? = null,
        var media_url: String? = null,
        var message: String? = null,
        var message_cover: String? = null,
        var message_keywords: String? = null,
        var message_length: String? = null,
        var message_status: String? = null,
        var message_title: String? = null,
        var pic: String? = null,
        var question_answer_num: String? = null,
        var question_follow_num: String? = null,
        var rank_score: String? = null,
        var recent_hot_reply_ids: String? = null,
        var recent_reply_ids: String? = null,
        var recommend: String? = null,
        var related_dyh_ids: String? = null,
        var relatednum: String? = null,
        var replynum: String? = null,
        var reportnum: String? = null,
        var source_id: String? = null,
        var status: String? = null,
        var tags: String? = null,
        var tid: String? = null,
        var tinfo: String? = null,
        var tpic: String? = null,
        var ttitle: String? = null,
        var turl: String? = null,
        var type: String? = null,
        var uid: String? = null,
        var user_tags: String? = null,
        var username: String? = null,
        var favnum: String? = null,
        var issummary: String? = null,
        var is_html_article: String? = null,
        var is_hidden: String? = null,
        var is_anonymous: String? = null,
        var id: String? = null,
        var fromid: String? = null,
        var forwardnum: String? = null,
        var fid: String? = null,
        var extra_type: String? = null,
        var extra_status: String? = null,
        var dyh_id: String? = null,
        var dateline: String? = null,
        var comment_block_num: String? = null,
        var block_status: String? = null,
        var index_name: String? = null,
        @SerializedName("_queryTotal")
        var queryTotal: Int = 0,
        @SerializedName("_queryViewTotal")
        var queryViewTotal: Int = 0,
        @SerializedName("_querySearchTime")
        var querySearchTime: Double = 0.0,
        var fetchType: String? = null,
        var entityId: String? = null,
        var avatarFetchType: String? = null,
        var userAvatar: String? = null,
        var entityTemplate: String? = null,
        var entityType: String? = null,
        var url: String? = null,
        var feedType: String? = null,
        var feedTypeName: String? = null,
        var turlTarget: String? = null,
        var info: String? = null,
        var infoHtml: String? = null,
        var media_info: String? = null,
        var sourceFeed: Any? = null,
        var forwardSourceType: String? = null,
        var shareUrl: String? = null,
        var title: String? = null,
        var extra_fromApi: String? = null,
        var userInfo: UserInfoBean? = null,
        @SerializedName("_tid")
        var tid2: String? = null,
        var replyRowsCount: Int = 0,
        var replyRowsMore: Int = 0,
        var picArr: List<String>? = null,
        var relateddata: List<*>? = null,
        var relationRows: List<RelationRowsBean>? = null,
        var replyRows: List<*>? = null
    ) {
        /**
         * uid : 427022
         * username : 不要看钟
         * admintype : 0
         * groupid : 0
         * usergroupid : 111
         * level : 11
         * status : 1
         * usernamestatus : 1
         * avatarstatus : 1585702779
         * avatar_cover_status : 1585671922
         * regdate : 1416118485
         * logintime : 1585733076
         * fetchType : base
         * entityType : user
         * entityId : 427022
         * displayUsername : 不要看钟
         * url : /u/427022
         * userAvatar : http://avatar.coolapk.com/data/000/42/70/22_avatar_middle.jpg?1585702779
         * userSmallAvatar : http://avatar.coolapk.com/data/000/42/70/22_avatar_small.jpg?1585702779
         * userBigAvatar : http://avatar.coolapk.com/data/000/42/70/22_avatar_big.jpg?1585702779
         * cover : http://avatar.coolapk.com/cover/15/85/67/427022_1585671922.jpg
         * verify_status : 0
         * verify_icon :
         * verify_label :
         * verify_title :
         */
        class UserInfoBean(
            var uid: Int = 0,
            var username: String? = null,
            var admintype: Int = 0,
            var groupid: Int = 0,
            var usergroupid: Int = 0,
            var level: Int = 0,
            var status: Int = 0,
            var usernamestatus: Int = 0,
            var avatarstatus: Int = 0,
            var avatar_cover_status: Int = 0,
            var regdate: Int = 0,
            var logintime: Int = 0,
            var fetchType: String? = null,
            var entityType: String? = null,
            var entityId: Int = 0,
            var displayUsername: String? = null,
            var url: String? = null,
            var userAvatar: String? = null,
            var userSmallAvatar: String? = null,
            var userBigAvatar: String? = null,
            var cover: String? = null,
            var verify_status: Int = 0,
            var verify_icon: String? = null,
            var verify_label: String? = null,
            var verify_title: String? = null
        )

        /**
         * id : 3036
         * logo : http://image.coolapk.com/dyh_logo/2019/0218/12/ab1cbaa511891eb84d340532cd55864e-dyh-uid-691103@694x694.jpg
         * title : 是一见钟情
         * url : /dyh/3036
         * entityType : feedRelation
         */
        data class RelationRowsBean(
            var id: Int = 0,
            var logo: String? = null,
            var title: String? = null,
            var url: String? = null,
            var entityType: String? = null
        )
    }
}