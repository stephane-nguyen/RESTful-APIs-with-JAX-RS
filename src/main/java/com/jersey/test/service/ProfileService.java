package com.jersey.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jersey.test.database.DatabaseClass;
import com.jersey.test.model.Profile;

public class ProfileService {
	private static Map<String, Profile> profiles = DatabaseClass.getProfiles();

	public ProfileService() {
		profiles.put("rekd", new Profile(1L, "rekd", "stephane", "nguyen"));
		profiles.put("alexia", new Profile(2L, "alexia", "louise", "kao"));
	}

	public List<Profile> getAllProfiles() {
		return new ArrayList<Profile>(profiles.values());
	}

	public Profile getProfile(String profileName) {
		return profiles.get(profileName);
	}

	public Profile updateProfile(Profile profile) {
		if (profile.getProfileName().isEmpty()) {
			return null; 
		}
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile addProfile(Profile profile) {
		profile.setId(profiles.size() + 1);
		profiles.put(profile.getProfileName(), profile);
		return profile;
	}

	public Profile removeProfile(String profileName) {
		return profiles.remove(profileName);
	}

}
