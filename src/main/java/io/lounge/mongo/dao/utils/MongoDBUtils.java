package io.lounge.mongo.dao.utils;

import io.lounge.api.utils.DAOUtils;
import io.lounge.mongo.dao.HashtagDAO;
import io.lounge.mongo.dao.domodels.HashtagDO;

import static io.lounge.configuration.LoungeConstants.COURSESNAMES;

public class MongoDBUtils {

	public void fillHashtagList() {
		HashtagDAO hashtagDAO = DAOUtils.getHashtagDAO();

		for (String courseName : COURSESNAMES) {
			hashtagDAO.createHashtag(new HashtagDO(courseName));
		}
	}
}
